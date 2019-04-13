package model.game_engine;

import com.google.java.contract.Ensures;
import console_view.ErrorMessage;
import model.board.Board;
import model.board.Tile;
import model.insect.Insect;
import model.insect.InsectGenerator;
import model.player.Player;

import java.util.List;

public class GameEngine {
    private Board board;
    private Player[] players;
    private int turn;
    private InsectGenerator insectGenerator;
    private Insect currentInsect;
    private List<Tile> currentValidTiles;
    private Mode mode;
    private ErrorMessage errorMessage;

    public GameEngine() {
        board = new Board();

        // TODO: left and right panels???

        players = new Player[]{new Player(), new Player()};
        turn = 0;
        insectGenerator = new InsectGenerator();
        mode = Mode.UNDEFINED;
        errorMessage = new ErrorMessage();
    }

    public void selectNewInsect(String insectType) {
        if (players[turn].reachedMaxInsects()) {
            // TODO: VIEW - display error (exception?)
            errorMessage.printError("Cannot add more insects.");
            return;
        }

        currentInsect = insectGenerator.createInsect(insectType);
        mode = Mode.PLACE;
        currentValidTiles = board.getValidPlaceTiles(turn);
    }

    // TODO: add methods that set mode to MOVE or ATTACK here - to be called from the controller

    public void processSelectedTile(int x, int y) {
        /**3 cases:
         * #1: The player click on the tile to select an existing insect to manipulate - currentInsect == null
         * #2: The player click on the tile to
         *      - place the insect: currentInsect.getCoordinate == null
         *      - move the insect there: valid selectedTile has no insect on it
         *      - attack the insect on that tile: valid selectedTile has an insect on it (MEH)
         * #3: There's nothing on that tile */

        Tile selectedTile = board.getTile(x, y);
        switch (mode) {
            case PLACE:
                placeInsectOnto(selectedTile);
                break;
            case MOVE:
                moveInsectTo(selectedTile);
                break;
            case ATTACK:
                attack(selectedTile);
                break;
        }
    }

    private void placeInsectOnto(Tile selectedTile) {
        if (validTileSelection(selectedTile)) {
            currentInsect.setTile(selectedTile);
            players[turn].placeInsect(currentInsect);
            board.registerInsect(currentInsect);
            toggleTurn();
        } else {
            // TODO: VIEW - display error
            errorMessage.printError("The insect cannot be placed on the selected tile.");
        }
    }

    // TODO
    private void moveInsectTo(Tile selectedTile) {
        if (validTileSelection(selectedTile)) {
            // TODO: some stuff
            toggleTurn();
        } else {
            // TODO: VIEW - display error
            errorMessage.printError("The insect cannot move to the selected tile.");
        }
    }

    // TODO
    private void attack(Tile selectedTile) {
        if (validTileSelection(selectedTile)) {
            // TODO: some other stuff
            toggleTurn();
        } else {
            // TODO: VIEW - display error
            errorMessage.printError("The insect cannot attack the selected tile.");
        }
    }

    private boolean validTileSelection(Tile selectedTile) {
        return currentValidTiles.contains(selectedTile);
    }

    @Ensures("old(turn) != turn")
    private void toggleTurn() {
        // Reset
        currentInsect = null;
        currentValidTiles = null;
        mode = Mode.UNDEFINED;

        // TODO: checkWin() and enable/disable ants/beetles

        // Switch to the other player
        turn = (turn % 2 == 0) ? 1 : 0;
    }
}
