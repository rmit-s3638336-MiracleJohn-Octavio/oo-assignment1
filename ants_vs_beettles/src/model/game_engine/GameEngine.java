package model.game_engine;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import console_view.BoardView;
import console_view.ErrorMessage;
import controller.DashboardVC;
import model.board.Board;
import model.board.Tile;
import model.game_engine.states.State;
import model.game_engine.states.UndefinedState;
import model.insect.Insect;
import model.insect.InsectFactory;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

@Invariant("turn >= 0 && turn <= 1")
public class GameEngine {
	private Board board;
	private Player[] players;
	private int turn;
	private Insect currentInsect;
	private List<Tile> currentValidTiles;
	private State state;

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

	public void setMode(String mode) {
		// TODO: healing goes here
	}

	public void processSelectedTile(int x, int y) {
		String msg = "";
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

	public boolean validTileSelection(Tile selectedTile) {
		return currentValidTiles.contains(selectedTile);
	}

	@Ensures("old(turn) != turn")
	public void toggleTurn() {
		reset();

		// TODO: checkWin()

		players[turn].deParalyseInsects();

		// Switch to the other player
		turn = getOpponentTurn();
		dashboardController.switchPlayer(turn);
	}

	public void reset() {
		currentInsect = null;
		currentValidTiles = new ArrayList<>();
	}

	private int getOpponentTurn() {
		return (turn + 1) % 2;
	}

	public void updateViews() {
		dashboardController.drawBoard(board.getAllTiles(), currentValidTiles, currentInsect);
		boardView.drawBoard(board.getAllTiles(), currentValidTiles);
	}

	public void updateError(String msg) {
		errorMessage.printError(msg);
		dashboardController.setErrorMessage(msg);
	}

//    public Player getCurrentPlayer() {
//    	return players[turn];
//    }
//    public void setCurrentInsectToNewInsect(String insectType) {
//    	currentInsect = insectFactory.createInsect(insectType);
//    }
//    
//    public void setCurrentValidTiles() {
//    	currentValidTiles = currentInsect.getValidPlaceTiles(board);
//    }
//    
	public void setState(State state) {
		this.state = state;
	}

//    public void setcurrentInsect(Insect insect) {
//    currentInsect=insect;
//    }
//    public Insect getCurrentInsect() {
//    	return  currentInsect;
//    }
	public void setNewInsect(String insectType) {
		if (players[turn].reachedMaxInsects()) {
			updateError("Cannot add more insects.");
			return;
		}

		currentInsect = insectFactory.createInsect(insectType);
		currentValidTiles = currentInsect.getValidPlaceTiles(board);

		updateViews();
	}
}
