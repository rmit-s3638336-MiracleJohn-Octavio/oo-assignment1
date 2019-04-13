package model.insect.beetles;

import model.board.Tile;
import model.insect.Profile;

import java.util.ArrayList;

public class Bogus extends Beetle {
    private static Profile profile = new Profile(0,0,0, 0);

    public Bogus() {
        super(profile);
    }

    @Override
    public String toString() {
        return "b";
    }

    @Override
    public ArrayList<Tile> getValidAttackTiles() {
        return null;
    }
}