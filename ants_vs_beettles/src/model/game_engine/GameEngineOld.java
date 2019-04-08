package model.game_engine;

import model.board.Board;
import model.board.Coordinate;
import model.board.TileOld;
import model.insect.Insect;
import model.insect.ants.Heavy;
import model.insect.ants.Ranger;
import model.insect.ants.Scout;
import model.insect.beetles.Bogus;
import model.insect.beetles.Finder;
import model.insect.beetles.Greedy;
import model.player.PlayerOld;
import view.BoardView;
import view.ErrorMessage;

import java.util.List;

public class GameEngineOld {
    private BoardView boardView = new BoardView();
    private Board board = new Board(boardView);
    private PlayerOld[] players = new PlayerOld[]{new PlayerOld(), new PlayerOld()};
    private int turn = 0;
    private Insect currentInsect = null;
    private List<TileOld> currentValidTiles = null;
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

    // TODO: add methods that set mode to MOVE or ATTACK here

//    @Requires("insectType != null")
    // TODO: Another way of creating a new insect - factory?
    private Insect newInsect(String insectType) {
        Insect insect = null;
        // Set current insect - this is such a bad way of implementing this but I can't think of any other way
        // NOTE: something to do with toString()?
        switch (insectType) {
            case "scout":
                insect = new Scout();
                break;
            case "heavy":
                insect = new Heavy();
                break;
            case "ranger":
                insect = new Ranger();
                break;
            case "finder":
                insect = new Finder();
                break;
            case "greedy":
                insect = new Greedy();
                break;
            case "bogus":
                insect = new Bogus();
                break;
        }

        return insect;
    }

    public void processSelectedTile(int x, int y) {
        /**3 cases:
         * #1: The player click on the tile to select an existing insect to manipulate - currentInsect == null
         * #2: The player click on the tile to
         *      - place the insect: currentInsect.getCoordinate == null
         *      - move the insect there: valid selectedTile has no insect on it
         *      - attack the insect on that tile: valid selectedTile has an insect on it
         * #3: There's nothing on that tile */

        TileOld selectedTile = board.getTile(new Coordinate(x, y));
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
                currentInsect = selectedTile.getInsect();
                // TODO: call VIEW (either here or in Board) to display the 2 options
        }
    }

    private void placeInsectOnto(TileOld selectedTile) {
//        if (validTileSelection(selectedTile)) {
//            currentInsect.setCoordinate(selectedTile.getCoordinate());
//            players[turn].placeInsect(currentInsect);
//            board.registerInsect(currentInsect);
//            toggleTurn();
//        } else {
//            // TODO: VIEW - display error
//            errorMessage.printError("The insect cannot be placed on the selected tile.");
//        }
    }

    // TODO
    private void moveInsectTo(TileOld selectedTile) {
        if (validTileSelection(selectedTile)) {
            // TODO: some stuff
            toggleTurn();
        } else {
            // TODO: VIEW - display error
            errorMessage.printError("The insect cannot move to the selected tile.");
        }
    }

    // TODO
    private void attack(TileOld selectedTile) {
        if (validTileSelection(selectedTile)) {
            // TODO: some other stuff
            toggleTurn();
        } else {
            // TODO: VIEW - display error
            errorMessage.printError("The insect cannot attack the selected tile.");
        }
    }

    private boolean validTileSelection(TileOld selectedTile) {
        return currentValidTiles.contains(selectedTile);
    }

    private void toggleTurn() {
        // Reset
        currentInsect = null;
        currentValidTiles = null;
        mode = null;

        // TODO: checkWin() and enable/disable ants/beetles

        // Switch to the other player
        turn = (turn % 2 == 0) ? 1 : 0;
    }

}
