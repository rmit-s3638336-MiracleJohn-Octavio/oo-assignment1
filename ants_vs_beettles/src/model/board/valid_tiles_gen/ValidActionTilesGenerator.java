package model.board.valid_tiles_gen;

import model.board.Board;
import model.board.Tile;
import model.insect.Insect;

import java.util.ArrayList;
import java.util.List;

public abstract class ValidActionTilesGenerator implements ValidTilesGenerator {
    // Template pattern
    @Override
    public List<Tile> getValidTiles(Insect insect, Board board, int hDir, int vDir, int range) {
        List<Tile> validTiles = new ArrayList<>();
        int x = insect.getTile().getX();
        int y = insect.getTile().getY();
        Tile tile;

        for (int i = 1; i <= range; i++) {
            x += hDir;
            y += vDir;
            tile = board.getTile(x, y);

            if (!addValidTile(validTiles, tile)) {
                break;
            }
        }

        return validTiles;
    }

    public abstract boolean addValidTile(List<Tile> validTiles, Tile tile);
}
