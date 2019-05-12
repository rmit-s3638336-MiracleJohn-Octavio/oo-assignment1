package model.insect.ants;

import model.board.Board;
import model.board.Tile;
import model.insect.Insect;
import model.insect.Profile;

import java.util.ArrayList;

public abstract class Ant extends Insect {
    public Ant(Profile profile) {
        super(profile);
    }

    public ArrayList<Tile> getValidMoveTiles(int x, int y, int xInc, int yInc, int range, Board board) {
        ArrayList<Tile> validTiles = new ArrayList<>();
        Tile tile;

        for (int i = 1; i <= range; i++) {
            x += xInc;
            y += yInc;
            tile = board.getTile(x, y);

            // Stop when meet an insect or reach the bound of the board
            if (tile == null || tile.getInsect() != null) {
                break;
            }

            validTiles.add(tile);
        }

        return validTiles;
    }

//    public abstract ArrayList<Tile> getValidAttackTiles();
}

