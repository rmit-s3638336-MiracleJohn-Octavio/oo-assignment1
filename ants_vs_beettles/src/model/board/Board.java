package model.board;

import model.insect.Insect;
import console_view.BoardView;

import java.util.ArrayList;

// TODO: make the list declaration more generic
public class Board {
    private static final int BOARD_SIZE = 10;

    // TODO: Extract to a new class???
    private static final int PLAYER_0_VALID_PLACING_COL = 0;
    private static final int PLAYER_1_VALID_PLACING_COL = BOARD_SIZE - 1;

    private Tile[][] tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
    private BoardView boardView;

    public Board(BoardView boardView) {
        this.boardView = boardView;
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

    public ArrayList<Tile> getValidPlaceTiles(int turn) {
        // TODO: Replace with a method?
        // Determine which player is playing
        int validCol = (turn == 0) ? PLAYER_0_VALID_PLACING_COL : PLAYER_1_VALID_PLACING_COL;

        // Generate a list of valid tiles (side edge tiles with no insect in it) that the insect can be placed on
        ArrayList<Tile> validTiles = new ArrayList<>();
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (tiles[row][validCol].getInsect() == null) {
                validTiles.add(tiles[row][validCol]);
            }
        }

        // TODO: Call VIEW method
        boardView.drawBoardWithValidTiles(tiles, validTiles);

        return validTiles;
    }

    public ArrayList<Tile> getValidMoveTiles(int turn, Insect insect) {
        // TODO

        return null;
    }

    public ArrayList<Tile> getValidAttackTiles(int turn, Insect insect) {
        // TODO

        return null;
    }

    // Keep a record of the insect in its current tile
    public void registerInsect(Insect insect) {
        tiles[insect.getCoordinate().getX()][insect.getCoordinate().getY()].setInsect(insect);
        boardView.drawBoard(tiles);
    }
}
