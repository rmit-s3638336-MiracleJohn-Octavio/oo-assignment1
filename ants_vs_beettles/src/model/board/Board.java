package model.board;

import model.insect.Insect;
import console_view.BoardView;

import java.util.ArrayList;

public class Board {
    private static final int BOARD_SIZE = 10;

    // TODO: Extract to a new class???
    private static final int PLAYER_1_VALID_PLACING_COL = 0;
    private static final int PLAYER_2_VALID_PLACING_COL = BOARD_SIZE - 1;

    private Tile[][] tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
    private BoardView boardView;

    public Board() {
        boardView = new BoardView();
        initBoard();
    }

    private void initBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                tiles[row][col] = new Tile(new Coordinate(row, col));
            }
        }

        boardView.drawBoard(tiles);
    }

    // Show the valid tiles that the insect can be placed on / be moved to / attack
    public ArrayList<Tile> getValidTiles(int player, String choice) {
        ArrayList<Tile> validTiles;

        // TODO: implement move and attack cases
        switch (choice) {
            case "add":
                validTiles = getValidPlaceTiles(player);
                break;
            default:
                validTiles = null;
                break;
        }

        boardView.drawBoardWithValidTiles(tiles, validTiles);
        return validTiles;
    }

    private ArrayList<Tile> getValidPlaceTiles(int player) {
        // TODO: Replace with a method?
        // Determine which player is playing
        int validCol = (player == 1) ? PLAYER_1_VALID_PLACING_COL : PLAYER_2_VALID_PLACING_COL;

        // Generate a list of valid tiles (side edge tiles with no insect in it) that the insect can be placed on
        ArrayList<Tile> validTiles = new ArrayList<>();
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (tiles[row][validCol].getInsect() == null) {
                validTiles.add(tiles[row][validCol]);
            }
        }

        return validTiles;
    }

    private ArrayList<Tile> getValidMoveTiles() {
        // TODO

        return null;
    }

    private ArrayList<Tile> getValidAttackTiles() {
        // TODO

        return null;
    }

    // Keep a record of the insect in its current tile
    public void registerInsect(Insect insect) {
        tiles[insect.getCoordinate().getX()][insect.getCoordinate().getY()].setInsect(insect);
        boardView.drawBoard(tiles);
    }
}
