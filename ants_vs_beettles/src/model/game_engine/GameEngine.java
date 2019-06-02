package model.game_engine;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import console_view.BoardView;
import console_view.ErrorMessage;
import controller.DashboardVC;
import model.board.Board;
import model.board.Tile;
import model.game_engine.commands.Command;
import model.game_engine.commands.FirstUndoCommand;
import model.game_engine.commands.SaveCommand;
import model.game_engine.commands.UndoCommand;
import model.game_engine.states.State;
import model.game_engine.states.UndefinedState;
import model.insect.Insect;
import model.insect.InsectFactory;
import model.insect.TargetSearcher;
import model.player.Player;
import model.player.Target;

import java.util.*;

// Memento pattern - Originator
// Command pattern - Invoker
// State pattern - Context
@Invariant("turn >= 0 && turn <= 1")
public class GameEngine {
	public static final int ANTS_TURN = 0;
	public static final int NUMBER_OF_PLAYERS = 2;

	private Board board;
	private Player[] players;
	private int turn;
	private Insect currentInsect;
	private List<Tile> currentValidTiles;
	private State state;

	private Command firstUndoCommand;
	private Command undoCommand;
	private Command saveCommand;
	private InsectFactory insectFactory;

	private BoardView boardView;
	private ErrorMessage errorMessage;
	private DashboardVC dashboardController;

	public GameEngine(int boardSize, int numOfInsects, DashboardVC dashboardController) {
		initGameParams(boardSize, numOfInsects,dashboardController);
		saveGame();
	}

	private void initGameParams(int boardSize, int numOfInsects, DashboardVC dashboardController) {
		board = new Board(boardSize);

		int targetRange = boardSize/ 2 - 1;
		Target antsTarget = new Target("donut",  boardSize, board.getBeetlesValidPlacingCol() - targetRange, board.getBeetlesValidPlacingCol());
		Player teamAnts = new Player("ants.Ant", antsTarget, numOfInsects);
		Target beetlesTarget = new Target("nest", boardSize, board.getAntsValidPlacingCol(), board.getAntsValidPlacingCol() + targetRange);
		Player teamBeetles = new Player("beetles.Beetle", beetlesTarget, numOfInsects);
		players = new Player[] {teamAnts, teamBeetles};

		turn = ANTS_TURN;
		currentValidTiles = new ArrayList<>();
		state = UndefinedState.getInstance();

		Caretaker caretaker = new Caretaker();
		firstUndoCommand = new FirstUndoCommand(caretaker);
		undoCommand = new UndoCommand(caretaker);
		saveCommand = new SaveCommand(caretaker);
		insectFactory = new InsectFactory();

		boardView = new BoardView();
		errorMessage = new ErrorMessage();

		initGUI(dashboardController, new Target[]{antsTarget, beetlesTarget});
	}

	private void initGUI(DashboardVC dashboardController, Target[] targets) {
		this.dashboardController = dashboardController;
		dashboardController.setGameEngine(this);
		dashboardController.initComponents(board.getAllTiles(), targets);
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

	public boolean setCurrentInsect(Tile selectedTile) {
		if (selectedTile.getInsect() != null && !selectedTile.getInsect().isParalysed()) {
			Insect insect = selectedTile.getInsect();
			if (players[turn].ownInsect(insect)) {
				currentInsect = insect;
				currentValidTiles = insect.getValidActionTiles(board);
				return true;
			}
		}

		return false;
	}

	public void processSelectedTile(int x, int y) {
		Tile selectedTile = board.getTile(x, y);
		state.processSelectedTile(this, selectedTile);
		updateViews();
	}

	public String actUpon(Tile selectedTile) {
		String msg = "";
		if (validTileSelection(selectedTile)) {
			if (selectedTile.getInsect() != null) {
				attack(selectedTile);
			} else {
				moveInsectTo(selectedTile);
			}
		} else {
			msg = "Invalid move";
		}

		toggleHeal(false);
		reset();
		return msg;
	}

	private void moveInsectTo(Tile selectedTile) {
		if (validTileSelection(selectedTile)) {
			currentInsect.getTile().resetInsect();
			selectedTile.setInsect(currentInsect);
			toggleTurn();
		}
	}

	private void attack(Tile selectedTile) {
		if (validTileSelection(selectedTile)) {
			currentInsect.attack(this, selectedTile.getInsect());
			toggleTurn();
		}
	}

	public String placeInsectOnto(Tile selectedTile) {
		if (validTileSelection(selectedTile)) {
			players[turn].addInsect();
			selectedTile.setInsect(currentInsect);
			toggleTurn();

			return "";
		}

		reset();
		return "The insect cannot be placed on the selected tile.";
	}

	private boolean validTileSelection(Tile selectedTile) {
		return currentValidTiles.contains(selectedTile);
	}

	public void heal(){
		currentInsect.heal();
		toggleHeal(false);
		state = UndefinedState.getInstance();
		toggleTurn();
		updateViews();
		updateError("");
	}

	public void toggleHeal(boolean displayHeal){
		dashboardController.toggleHeal(displayHeal);
	}

	@Ensures("old(turn) != turn")
	private void toggleTurn() {
		if (win()) {
			String winner = players[turn].getTeamName();
			dashboardController.declareWinner(winner);

			return;
		}

		reset();

		if (players[turn].usedUndo()) {
			players[turn].switchOffUndo();
		}

		// Switch to the other player
		turn = getOpponentTurn();
		dashboardController.switchPlayer(turn);
		saveGame();
	}

	private void reset() {
		currentInsect = null;
		currentValidTiles = new ArrayList<>();
	}

	private boolean win() {
		if (currentInsect instanceof TargetSearcher) {
			return ((TargetSearcher) currentInsect).foundTarget(currentInsect, players[turn].getTarget());
		}
		return false;
	}

	private int getOpponentTurn() {
		return (turn + 1) % NUMBER_OF_PLAYERS;
	}

	public void clickedUndo() {
		if (players[turn].undoable()) {
			if (players[turn].firstUndo()) {
				firstUndoCommand.execute(this);
			} else {
				undoCommand.execute(this);
			}

			updateViews();
		} else {
			updateError("You've used up your undos.");
		}
	}

	public void undo(GameEngineMemento gameEngineMemento) {
		this.board = gameEngineMemento.getBoard();
		reset();
		resetState();
	}

	private void saveGame() {
		saveCommand.execute(this);
	}

	public GameEngineMemento save() {
		return new GameEngineMemento(board);
	}

	public void setState(State state) {
		this.state = state;
	}

	private void resetState() {
		state = UndefinedState.getInstance();
	}

	public Board getBoard() {
		return board;
	}

	public Player getCurrentPlayer() {
		return players[turn];
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
