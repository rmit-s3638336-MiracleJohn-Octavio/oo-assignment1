package model.board;

public class Board {
    // TODO: public attributes???
    public static final int BOARD_SIZE = 10;
    public static final int PLAYER_0_VALID_PLACING_COL = 0;
    public static final int PLAYER_1_VALID_PLACING_COL = BOARD_SIZE - 1;

    private Tile[][] tiles = new Tile[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        initBoard();
    }

    public Tile[][] getAllTiles() {
        return tiles;
    }

    // GRASP - Creator + Info Expert
    private void initBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Tile tile = new Tile(row, col);
                tiles[row][col] = tile;
            }
        }
    }

    public Tile getTile(int x, int y) {
        return containsTile(x, y) ? tiles[x][y] : null;
    }

    private boolean containsTile(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }
}
