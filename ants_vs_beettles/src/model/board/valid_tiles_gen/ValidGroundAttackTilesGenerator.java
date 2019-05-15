package model.board.valid_tiles_gen;

import model.board.Tile;

import java.util.List;

public class ValidGroundAttackTilesGenerator extends ValidActionTilesGenerator {
    @Override
    public boolean addValidTile(List<Tile> validTiles, Tile tile) {
        if (tile == null) {
            return false;
        }

        if (tile.getInsect() != null) {
            validTiles.add(tile);
            return false;
        }

        return true;
    }
}
