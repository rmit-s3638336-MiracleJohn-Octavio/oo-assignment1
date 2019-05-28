package model.board.valid_tiles_gen;

import model.board.Board;
import model.board.Tile;
import model.insect.Insect;
import model.insect.ants.Ant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidPlaceTilesGenerator implements ValidTilesGenerator {
    @Override
    public List<Tile> getValidTiles(Insect insect, Board board, int xInc, int yInc, int range) {
        int startingCol = (insect instanceof Ant) ? board.getAntsValidPlacingCol() : board.getBeetlesValidPlacingCol();
        Tile[][] tiles = board.getAllTiles();

        ArrayList<Tile> validTiles = new ArrayList<>();
        for (int row = 0; row < board.getBoardSize(); row += yInc) {
            int col = startingCol;
            int counter = range;
            while (counter > 0) {
                if (tiles[row][col].getInsect() == null) {
                    validTiles.add(tiles[row][col]);
                }

                col += xInc;
                counter--;
            }
        }

        Collections.sort(validTiles);
        return validTiles;
    }
}
