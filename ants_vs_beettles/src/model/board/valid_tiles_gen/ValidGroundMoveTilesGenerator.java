package model.board.valid_tiles_gen;

import model.board.Tile;

import java.util.List;

public class ValidGroundMoveTilesGenerator extends ValidActionTilesGenerator {
    @Override
    public boolean addValidTile(List<Tile> validTiles, Tile tile) {
        // Stop when meet an insect or reach the bound of the board
        if (tile == null || tile.getInsect() != null) {
            return false;
        }

        validTiles.add(tile);
        return true;
    }
}
