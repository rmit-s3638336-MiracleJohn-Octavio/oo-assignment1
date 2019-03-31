package model.game_engine;

import console_view.BoardView;
import console_view.ErrorMessage;
import model.board.Board;
import model.board.Tile;
import model.insect.Insect;
import model.insect.ants.Scout;
import model.insect.beetles.Finder;
import model.player.Player;

import java.util.List;

public class GameEngine {
    private BoardView boardView = new BoardView();
    private Board board = new Board(boardView);
    private Player[] players = new Player[]{new Player(), new Player()};
    private int turn = 0;
    private Insect currentInsect = null;
    private List<Tile> currentValidTiles = null;
    private Mode mode = null;

    private ErrorMessage errorMessage = new ErrorMessage();

    public void selectNewInsect(String insectType) {
        if (players[turn].reachedMaxInsects()) {
            // TODO: VIEW - display error (exception?)
            errorMessage.printError("Cannot add more insects.");
            return;
        }

        currentInsect = newInsect(insectType);
        mode = Mode.PLACE;
        currentValidTiles = board.getValidPlaceTiles(turn);
    }

    private Insect newInsect(String insectType) {
        Insect insect = null;
        // Set current insect - this is such a bad way of implementing this but I can't think of any other way
        // NOTE: something to do with toString()?
        switch (insectType) {
            case "scout":
                insect = new Scout();
                break;
            case "finder":
                insect = new Finder();
                break;
            // Other cases
        }

        return insect;
    }

    public void processSelectedTile(Tile tile) {
        /**3 cases:
         * #1: The player click on the tile to select an existing insect to manipulate
         * #2: The player click on the tile to place the insect / move the insect there / attack the insect on that tile
         * #3: There's nothing on that tile */
        switch (mode) {
            case PLACE:
                if (validTile(tile)) {
                    currentInsect.setCoordinate(tile.getCoordinate());
                    players[turn].placeInsect(currentInsect);
                    board.registerInsect(currentInsect);
                    toggleTurn();
                } else {
                    // TODO: VIEW - display error
                    errorMessage.printError("The insect cannot be placed on the selected tile.");
                }
                break;
            case MOVE:
                if (validTile(tile)) {
                    // TODO: some stuff
                    toggleTurn();
                } else {
                    // TODO: VIEW - display error
                    errorMessage.printError("The insect cannot move to the selected tile.");
                }
                break;
            case ATTACK:
                if (validTile(tile)) {
                    // TODO: some other stuff
                    toggleTurn();
                } else {
                    // TODO: VIEW - display error
                    errorMessage.printError("The insect cannot attack the selected tile.");
                }
                break;
            default:
                // TODO: Check if there's an insect on that tile: YES: set currentInsect; NO: do nothing
        }
    }

    private boolean validTile(Tile tile) {
        return currentValidTiles.contains(tile);
    }

    private void toggleTurn() {
        // Reset
        currentInsect = null;
        currentValidTiles = null;
        mode = null;

        // Switch to the other player
        turn = (turn % 2 == 0) ? 1 : 0;
    }
}
