package model.board;

public class Board {
    private Tile[][] tiles;

    public Board(int boardSize) {
        tiles = new Tile[boardSize][boardSize];
        initBoard();
    }

    public Board(Board board) {
        initClone(board);
    }

    public Tile[][] getAllTiles() {
        return tiles;
    }

    // GRASP - Creator + Info Expert
    private void initBoard() {
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                tiles[row][col] = new Tile(row, col);
            }
        }
    }

    private void initClone(Board board) {
        tiles = new Tile[board.getBoardSize()][board.getBoardSize()];
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                tiles[row][col] = new Tile(board.getTile(row, col));
            }
        }
    }

    public Tile getTile(int x, int y) {
        return containsTile(x, y) ? tiles[x][y] : null;
    }

    private boolean containsTile(int x, int y) {
        return x >= 0 && x < tiles.length && y >= 0 && y < tiles.length;
    }

    public int getBoardSize() {
        return tiles.length;
    }

    public int getAntsValidPlacingCol() {
        return 0;
    }

    public int getBeetlesValidPlacingCol() {
        return tiles.length - 1;
    }
}
