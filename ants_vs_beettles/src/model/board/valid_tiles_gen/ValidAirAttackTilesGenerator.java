package model.board.valid_tiles_gen;

import model.board.Tile;

import java.util.List;

public class ValidAirAttackTilesGenerator extends ValidActionTilesGenerator {
    @Override
    public boolean addValidTile(List<Tile> validTiles, Tile tile) {
        // Stop when reach the bound of the board
        if (tile == null) {
            return false;
        }

        if (tile.getInsect() != null) {
            validTiles.add(tile);
        }

        return true;
    }
}
