package model.insect.beetles;

import model.board.Tile;
import model.insect.Profile;

import java.util.ArrayList;

public class Finder extends Beetle {
    private static Profile profile = new Profile(0,0,0, 0);

    public Finder() {
        super(profile);
    }

    @Override
    public String toString() {
        return "f";
    }

    @Override
    public ArrayList<Tile> getValidAttackTiles() {
        return null;
    }
}
