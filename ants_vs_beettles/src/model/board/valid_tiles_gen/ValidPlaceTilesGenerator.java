package model.board.valid_tiles_gen;

import model.board.Board;
import model.board.Tile;
import model.insect.Insect;
import model.insect.ants.Ant;

import java.util.ArrayList;
import java.util.List;

public class ValidPlaceTilesGenerator implements ValidTilesGenerator {
    @Override
    public List<Tile> getValidTiles(Insect insect, Board board) {
        int validCol = (insect instanceof Ant) ? Board.PLAYER_0_VALID_PLACING_COL : Board.PLAYER_1_VALID_PLACING_COL;

        // Generate a list of valid tiles (side edge tiles with no insect in it) that
        // the insect can be placed on
        ArrayList<Tile> validTiles = new ArrayList<>();
        Tile[][] tiles = board.getAllTiles();
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            if (tiles[row][validCol].getInsect() == null) {
                validTiles.add(tiles[row][validCol]);
            }
        }

        return validTiles;
    }
}
