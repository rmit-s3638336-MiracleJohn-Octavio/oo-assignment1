package controller;

import console_view.BoardView;
import model.board.Board;
import model.board.Coordinate;
import model.board.Tile;
import model.helper.Helper;
import model.helper.Mode;
import model.insect.Insect;
import model.insect.ants.Scout;
import model.player.Player;

import java.util.List;

public class Main {
    // TODO: move these variables to helper?
    private static BoardView boardView = new BoardView();
    private static Board board = new Board(boardView);
    private static Player[] players = new Player[]{new Player(), new Player()};
    private static int turn;
    private static Insect currentInsect;
    private static List<Tile> currentValidTiles;
    private static Mode mode;

    public static void main(String[] args) {
        // Init the game
        Helper helper = new Helper();

        // TODO: Generate the food (which class???) - TO BE IMPLEMENTED IN THE SECOND ASSIGNMENT?

        // GAME LOOP
        // TODO: change condition to checkWin()
        boolean keepOnLooping = true;
        while (keepOnLooping) {
            // TODO: enable and disable insects; I HAVE NO IDEA HOW TO DO THIS!!!!
            mockSelectNewInsect();
            mockSelectTile();
            keepOnLooping = false;
        }
    }

    // TODO: move these methods to Helper
    private static void toggleTurn() {
        // Reset
        currentInsect = null;
        currentValidTiles = null;
        mode = null;

        // Switch to the other player
        turn = (turn % 2 == 0) ? 1 : 0;
    }

    // TODO: some event handlers

    // Mock handler
    public static void mockSelectNewInsect() {
        // insectType: to be parsed in
        String insectType = "scout";

        if (players[turn].reachedMaxInsects()) {
            // display error (exception?)
        }

        // Set current insect - this is such a bad way of implementing this but I can't think of any other way
        // NOTE: something to do with toString()?
        switch (insectType) {
            case "scout":
                currentInsect = new Scout();
                break;
            // Other cases
        }

        mode = Mode.PLACE;
        currentValidTiles = board.getValidPlaceTiles(turn);
    }

    // Mock handler
    public static void mockSelectTile() {
        // tileCoord: to be parsed in
        String tileCoord = "0_0";

        // TODO: method to extract x and y from tileCoord
        int x = 0;
        int y = 0;

        Tile selectedTile = new Tile(new Coordinate(x, y));
        processSelectedTile(selectedTile);
    }

    public static void processSelectedTile(Tile tile) {
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
                    // TODO display error
                }
                break;
            case MOVE:
                if (validTile(tile)) {
                    // do some stuff
                    toggleTurn();
                } else {
                    // display error
                }
                break;
            case ATTACK:
                if (validTile(tile)) {
                    // do some other stuff
                    toggleTurn();
                } else {
                    // display error
                }
                break;
            default:
                // Check if there's an insect on that tile: YES: set currentInsect; NO: do nothing
        }
    }

    private static boolean validTile(Tile tile) {
        return currentValidTiles.contains(tile);
    }
}