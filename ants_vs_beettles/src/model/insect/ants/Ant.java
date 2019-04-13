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

    public void walk() {


        // TODO
    }

    public void groundAttack() {
        // TODO
    }

//    public ArrayList<Tile> getValidMoveTiles(Board board) {
//        ArrayList<Tile> validTiles = new ArrayList<>();
//
//        Tile insectLoc = this.getTile();
//        int x = insectLoc.getX();
//        int y = insectLoc.getY();
//        int range = this.getProfile().getSpeed();
//
//        validTiles.addAll(getValidTiles(x, y, 1, 0, range, board));
//        validTiles.addAll(getValidTiles(x, y, -1, 0, range, board));
//        validTiles.addAll(getValidTiles(x, y, 0, 1, range, board));
//        validTiles.addAll(getValidTiles(x, y, 0, -1, range, board));
//
//        return validTiles;
//    }

    public ArrayList<Tile> getValidMoveTiles(int x, int y, int xInc, int yInc, int range, Board board) {
        ArrayList<Tile> validTiles = new ArrayList<>();
        Tile tile;

        for (int i = 1; i <= range; i++) {
            x += xInc;
            y += yInc;
            tile = board.getTile(x, y);

            if (tile == null || tile.getInsect() != null) {
                break;
            }

            validTiles.add(tile);
        }

        return validTiles;
    }
}

