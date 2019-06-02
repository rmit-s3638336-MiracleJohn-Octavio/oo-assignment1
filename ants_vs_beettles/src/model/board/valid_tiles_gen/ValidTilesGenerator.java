package model.board.valid_tiles_gen;

import model.board.Board;
import model.board.Tile;
import model.insect.Insect;

import java.util.List;

public interface ValidTilesGenerator {
    public static int WEST = -1;
    public static int EAST = 1;
    public static int NORTH = -1;
    public static int SOUTH = 1;
    public static int NULL = 0;

    public abstract List<Tile> getValidTiles(Insect insect, Board board, int hDir, int vDir, int range);
}
