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
import model.insect.ants.Ant;
import model.insect.beetles.Beetle;
import model.player.Player;
import model.target.Donut;
import model.target.Leaf;

import java.util.*;

@Invariant("turn >= 0 && turn <= 1")
public class GameEngine {
	private final int ANT = 0;
	private final int BEETLE = 1;
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

	public GameEngine(DashboardVC dashboardController) {
		initGameParams();
		initGUI(dashboardController);
		saveGame();
	}

	private void initGameParams() {
		board = new Board();
		players = new Player[] {new Player(new Donut()), new Player(new Leaf())};
		turn = 0;
		currentValidTiles = new ArrayList<>();
		state = UndefinedState.getInstance();
		generateTarget();

		Caretaker caretaker = new Caretaker();
		firstUndoCommand = new FirstUndoCommand(caretaker);
		undoCommand = new UndoCommand(caretaker);
		saveCommand = new SaveCommand(caretaker);
		insectFactory = new InsectFactory();

		boardView = new BoardView();
		errorMessage = new ErrorMessage();
	}

	private void initGUI(DashboardVC dashboardController) {
		this.dashboardController = dashboardController;
		dashboardController.setGameEngine(this);
		dashboardController.initComponents();
		dashboardController.loadPanels();

		dashboardController.initBoard(board.getAllTiles());
//		updateViews();
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
		if (players[turn].undoable()) {
		    // TODO
			if (players[turn].firstUndo()) {
				firstUndoCommand.execute(this);
			} else {
				undoCommand.execute(this);
			}

			updateViews();
		} else {
			updateError("Undo is not available.");
		}

	}

	public void heal(){
		System.out.println("GameEngine.heal()");
		currentInsect.heal();
		toggleHeal(false);
		state = UndefinedState.getInstance();
		toggleTurn();
		updateViews();
	}

	public void toggleHeal(boolean displayHeal){
		System.out.println("HEALLLLL: "+ displayHeal);
		dashboardController.toggleHeal(displayHeal);
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
			if (correctType(insect)) {
				currentInsect = insect;
				currentValidTiles = insect.getValidActionTiles(board);
				return true;
			}
		}
		return false;
	}

	private boolean correctType(Insect insect) {
		return turn == ANT && insect instanceof Ant || turn == BEETLE && insect instanceof Beetle;
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

	public String actUpon(Tile selectedTile) {
		if (validTileSelection(selectedTile)) {
			if (selectedTile.getInsect() != null) {
				attack(selectedTile);
			} else {
				moveInsectTo(selectedTile);
			}

			return "";
		}

		toggleHeal(false);
		reset();
		return "Invalid move";
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
			currentInsect.attack(board, players[getOpponentTurn()], selectedTile.getInsect());
			toggleTurn();
		}
	}

	private boolean validTileSelection(Tile selectedTile) {
		return currentValidTiles.contains(selectedTile);
	}

	@Ensures("old(turn) != turn")
	private void toggleTurn() {
		reset();
        if (players[turn].usedUndo()) {
            players[turn].switchOffUndo();
        }

		// TODO: checkWin()

		// Switch to the other player
		turn = getOpponentTurn();
		dashboardController.switchPlayer(turn);
		saveGame();
	}

	private void reset() {
		currentInsect = null;
		currentValidTiles = new ArrayList<>();
	}

	private int getOpponentTurn() {
		return (turn + 1) % NUMBER_OF_PLAYERS;
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
		System.out.println("\n\n-------------------SAVING---------------------");
		boardView.drawBoard(board.getAllTiles(), currentValidTiles);
		System.out.println("\n----------------------------------------------");
		return new GameEngineMemento(board);
	}

	public void undo(GameEngineMemento gameEngineMemento) {
		System.out.println("Loading board number " + gameEngineMemento.getCounter());
		this.board = gameEngineMemento.getBoard();
		reset();
		resetState();
	}

	public void updateViews() {
		dashboardController.drawBoard(board.getAllTiles(), currentValidTiles, currentInsect);
		boardView.drawBoard(board.getAllTiles(), currentValidTiles);
	}

	public void updateError(String msg) {
		errorMessage.printError(msg);
		dashboardController.setErrorMessage(msg);
	}

    private void generateTarget() {
        for (Player player : players) {
            System.out.println(player.getTarget().getFullName());

            // Generate a random row and col for Target
            // This is done only once
            player.getTarget().generateCoordinate();
        }
    }

    public Player[] getPlayers() {
        return players;
    }
}
