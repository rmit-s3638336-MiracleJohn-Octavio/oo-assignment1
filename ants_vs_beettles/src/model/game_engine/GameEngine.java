package model.game_engine;

import com.google.java.contract.Ensures;
import console_view.BoardView;
import console_view.ErrorMessage;
import model.board.Board;
import model.board.Tile;
import model.insect.Insect;
import model.insect.InsectGenerator;
import model.player.Player;

import java.util.List;

public class GameEngine {
    private BoardView boardView = new BoardView();
    private Board board = new Board(boardView);
    private Player[] players = new Player[]{new Player(), new Player()};
    private int turn = 0;
    private InsectGenerator insectGenerator = new InsectGenerator();
    private Insect currentInsect = null;
    private List<Tile> currentValidTiles = null;
    private Mode mode = Mode.UNDEFINED;
    private ErrorMessage errorMessage = new ErrorMessage();

    public int getTurn() {
        return turn;
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

    // TODO: add methods that set mode to MOVE or ATTACK here


    public void processSelectedTile(int x, int y) {
        /**3 cases:
         * #1: The player click on the tile to select an existing insect to manipulate - currentInsect == null
         * #2: The player click on the tile to
         *      - place the insect: currentInsect.getCoordinate == null
         *      - move the insect there: valid selectedTile has no insect on it
         *      - attack the insect on that tile: valid selectedTile has an insect on it
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
            default:
                // Select an insect to play
//                currentInsect = selectedTile.getInsect();
                // TODO: call VIEW (either here or in Board) to display the 2 options
        }
    }

    private void placeInsectOnto(Tile selectedTile) {
        if (validTileSelection(selectedTile)) {
            currentInsect.setCoordinate(selectedTile.getCoordinate());
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
