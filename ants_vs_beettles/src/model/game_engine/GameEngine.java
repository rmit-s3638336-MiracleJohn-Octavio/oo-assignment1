package model.game_engine;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import console_view.BoardView;
import console_view.ErrorMessage;
import controller.DashboardVC;
import model.board.Board;
import model.board.Tile;
import model.game_engine.commands.Command;
import model.game_engine.commands.SaveCommand;
import model.game_engine.commands.UndoCommand;
import model.game_engine.states.State;
import model.game_engine.states.UndefinedState;
import model.insect.Insect;
import model.insect.InsectFactory;
import model.player.Player;

import java.util.*;

@Invariant("turn >= 0 && turn <= 1")
public class GameEngine {
	private Board board;
	private Player[] players;
	private int turn;
	private Insect currentInsect;
	private List<Tile> currentValidTiles;
	private State state;

	private Command undoCommand;
	private Command saveCommand;
	private InsectFactory insectFactory;

	private BoardView boardView;
	private ErrorMessage errorMessage;
	private DashboardVC dashboardController;

	public GameEngine(DashboardVC dashboardController) {
		initGameParams();
		initGUI(dashboardController);
	}

	private void initGameParams() {
		board = new Board();
		players = new Player[] { new Player(), new Player() };
		turn = 0;
		currentValidTiles = new ArrayList<>();
		state = UndefinedState.getInstance();

		Caretaker caretaker = new Caretaker();
		undoCommand = new UndoCommand(caretaker);
		saveCommand = new SaveCommand(caretaker);
		insectFactory = new InsectFactory();

		boardView = new BoardView();
		errorMessage = new ErrorMessage();
	}

	private void initGUI(DashboardVC dashboardController) {
		this.dashboardController = dashboardController;
		dashboardController.setGameEngine(this);
		dashboardController.loadPanels();

		updateViews();
	}

	public void selectNewInsect(String insectType) {
		state.selectNewInsect(this, insectType);
	}

	public void setNewInsect(String insectType) {
		if (players[turn].reachedMaxInsects()) {
			updateError("Cannot add more insects.");
			return;
		}

		currentInsect = insectFactory.createInsect(insectType);
		currentValidTiles = currentInsect.getValidPlaceTiles(board);

		updateViews();
	}

	public void clickedUndo() {
		// TODO: check undo limit
		if (!players[turn].reachedUndoLimit()) {
			undoCommand.execute(this);
		} else {
			updateError("Meh");
		}

	}

	public void setMode(String mode) {
		// TODO: healing goes here
	}

	public void processSelectedTile(int x, int y) {
		Tile selectedTile = board.getTile(x, y);
		state.processSelectedTile(this, selectedTile);
		updateViews();
	}

	public boolean setCurrentInsect(Tile selectedTile) {
		if (selectedTile.getInsect() != null && !selectedTile.getInsect().isParalysed()) {
			Insect insect = selectedTile.getInsect();
			if (players[turn].containsInsect(insect)) {
				currentInsect = insect;
				currentValidTiles = insect.getValidActionTiles(board);
				return true;
			}
		}
		return false;
	}

	public String placeInsectOnto(Tile selectedTile) {
		if (validTileSelection(selectedTile)) {
			int id = players[turn].placeInsect(currentInsect);
			currentInsect.initInsect(id, selectedTile);
			selectedTile.setInsect(currentInsect);
			toggleTurn();

			return "";
		}

		reset();
		return "The insect cannot be placed on the selected tile.";
	}

	public String actUpon(Tile selectedTile) {
		if (validTileSelection(selectedTile)) {
			if (selectedTile.getInsect() != null) {
				attack(selectedTile);
			} else {
				moveInsectTo(selectedTile);
			}

			return "";
		}

		reset();
		return "Invalid move";
	}

	private void moveInsectTo(Tile selectedTile) {
		if (validTileSelection(selectedTile)) {
			currentInsect.getTile().resetInsect();
			currentInsect.setTile(selectedTile);
			selectedTile.setInsect(currentInsect);
			toggleTurn();
		}
	}

	private void attack(Tile selectedTile) {
		if (validTileSelection(selectedTile)) {
			currentInsect.attack(board, players[getOpponentTurn()], selectedTile.getInsect());
			toggleTurn();
		}
	}

	private boolean validTileSelection(Tile selectedTile) {
		return currentValidTiles.contains(selectedTile);
	}

	@Ensures("old(turn) != turn")
	private void toggleTurn() {
		// TODO
//		saveGame();
		reset();

		// TODO: checkWin()

		players[turn].deParalyseInsects();

		// Switch to the other player
		turn = getOpponentTurn();
		dashboardController.switchPlayer(turn);
	}

	private void reset() {
		currentInsect = null;
		currentValidTiles = new ArrayList<>();
	}

	private int getOpponentTurn() {
		return (turn + 1) % 2;
	}

	public void setState(State state) {
		this.state = state;
	}

	private void resetState() {
		state = UndefinedState.getInstance();
	}

	private void saveGame() {
		saveCommand.execute(this);
	}

	public GameEngineMemento save() {
		// Clone board
		Board currBoard = new Board(board);
		// Clone players
		Player[] currPlayers = new Player[2];

		for (int i = 0; i < players.length; i++) {
			Map<Integer, Insect> currInsects = players[i].getInsects();
			Player currPlayer = new Player(players[i]);

			// Clone insects and add them to the board
			Iterator iterator = currInsects.keySet().iterator();
			while (iterator.hasNext()) {
				int id = (int) iterator.next();
				Insect oldInsect = currInsects.get(id);

				// TODO: 2 clone methods
				Insect insect = oldInsect.cloneInsect();

				insect.initInsect(id, currentInsect.getTile());
				currPlayer.addInsectWithId(id, insect);
				currBoard.getTile(insect.getTile().getX(), insect.getTile().getY()).setInsect(insect);
				insect.setTile(currBoard.getTile(insect.getTile().getX(), insect.getTile().getY()));
			}

			currPlayers[i] = currPlayer;
		}

		GameEngineMemento gem = new GameEngineMemento(board, players);
		board = currBoard;
		players = currPlayers;

		return gem;
	}

	public void undo(GameEngineMemento gameEngineMemento) {
		this.board = gameEngineMemento.getBoard();
		this.players = gameEngineMemento.getPlayers();
		reset();
		resetState();
		updateViews();
	}

	public void updateViews() {
		dashboardController.drawBoard(board.getAllTiles(), currentValidTiles, currentInsect);
		boardView.drawBoard(board.getAllTiles(), currentValidTiles);
	}

	public void updateError(String msg) {
		errorMessage.printError(msg);
		dashboardController.setErrorMessage(msg);
	}
}
