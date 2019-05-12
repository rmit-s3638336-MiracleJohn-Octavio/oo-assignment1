package model.board.valid_tiles_gen;

import model.board.Board;
import model.board.Tile;
import model.insect.Insect;

import java.util.List;

public interface ValidTilesGenerator {
    public List<Tile> getValidTiles(Insect insect, Board board);
}
