package model.game_engine;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import console_view.BoardView;
import console_view.ErrorMessage;
import controller.DashboardVC;
import model.board.Board;
import model.board.Tile;
import model.insect.Insect;
import model.insect.InsectGenerator;
import model.player.Player;
import model.target.Donut;
import model.target.Leaf;

import java.util.ArrayList;
import java.util.List;

@Invariant("turn >= 0 && turn <= 1")
public class GameEngine {
    private Board board;
    private Player[] players;
    private int turn;
    private InsectGenerator insectGenerator;
    private Insect currentInsect;
    private List<Tile> currentValidTiles;
    private Mode mode;

    private BoardView boardView;
    private ErrorMessage errorMessage;
    private DashboardVC dashboardController;

    public GameEngine(DashboardVC dashboardController) {
        initGameParams();
        initGUI(dashboardController);
    }

    private void initGameParams() {
        board = new Board();
        players = new Player[]{new Player(new Donut()), new Player(new Leaf())};
        turn = 0;
        insectGenerator = new InsectGenerator();
        currentValidTiles = new ArrayList<>();
        mode = Mode.UNDEFINED;
        boardView = new BoardView();
        errorMessage = new ErrorMessage();

        generateTarget();
    }

    private void initGUI(DashboardVC dashboardController) {
        this.dashboardController = dashboardController;
        dashboardController.setGameEngine(this);
        dashboardController.loadPanels();

        updateViews();
    }

    public void selectNewInsect(String insectType) {
        if (players[turn].reachedMaxInsects()) {
            errorMessage.printError("Cannot add more insects.");
            dashboardController.setErrorMessage("Cannot add more insects.");
            return;
        }

        currentInsect = insectGenerator.createInsect(insectType);
        mode = Mode.PLACE;
        currentValidTiles = board.getValidPlaceTiles(turn);

        updateViews();
    }

    public void setMode(String mode) {
        if (currentInsect != null && currentInsect.getTile() != null) {
            if (mode.equals("move")) {
                this.mode = Mode.MOVE;
                currentValidTiles = board.getValidMoveTiles(currentInsect);
            } else {
                this.mode = Mode.ATTACK;
                currentValidTiles = board.getValidAttackTiles(currentInsect);
            }
        } else {
            errorMessage.printError("No insect selected.");
            dashboardController.setErrorMessage("No insect selected.");
        }

        updateViews();
    }

    public void processSelectedTile(int x, int y) {
        String msg = "";
        Tile selectedTile = board.getTile(x, y);
        switch (mode) {
            case PLACE:
                msg = placeInsectOnto(selectedTile);
                reset();
                break;
            case MOVE:
                msg = moveInsectTo(selectedTile);
                reset();
                break;
            case ATTACK:
                msg = attack(selectedTile);
                reset();
                break;
            case UNDEFINED:
                setCurrentInsect(selectedTile);
        }

        errorMessage.printError(msg);
        dashboardController.setErrorMessage(msg);

        updateViews();
    }

    private void setCurrentInsect(Tile selectedTile) {
        if (selectedTile != null) {
            Insect insect = selectedTile.getInsect();
            if (players[turn].containsInsect(insect)) {
                currentInsect = insect;
            }
        }
    }

    private String placeInsectOnto(Tile selectedTile) {
        if (validTileSelection(selectedTile)) {
            currentInsect.setTile(selectedTile);
            players[turn].placeInsect(currentInsect);
            selectedTile.setInsect(currentInsect);
            toggleTurn();

            return "";
        }

        return "The insect cannot be placed on the selected tile.";
    }

    private String moveInsectTo(Tile selectedTile) {
        if (validTileSelection(selectedTile)) {
            currentInsect.getTile().resetInsect();
            currentInsect.setTile(selectedTile);
            selectedTile.setInsect(currentInsect);
            toggleTurn();

            return "";
        }
        return "The insect cannot move to the selected tile.";
    }

    // TODO
    private String attack(Tile selectedTile) {
        if (validTileSelection(selectedTile)) {
            // TODO: some other stuff
            toggleTurn();

            return "";
        }

        return "The insect cannot attack the selected tile.";
    }

    private boolean validTileSelection(Tile selectedTile) {
        return currentValidTiles.contains(selectedTile);
    }

    @Ensures("old(turn) != turn")
    private void toggleTurn() {
        reset();

        // TODO: checkWin() and enable/disable ants/beetles

        // Switch to the other player
        turn = (turn % 2 == 0) ? 1 : 0;

        dashboardController.switchPlayer(turn);
    }

    private void reset() {
        currentInsect = null;
        currentValidTiles = new ArrayList<>();
        mode = Mode.UNDEFINED;
    }

    private void updateViews() {
        dashboardController.drawBoard(board.getAllTiles(), currentValidTiles, currentInsect);
        boardView.drawBoard(board.getAllTiles(), currentValidTiles);
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
