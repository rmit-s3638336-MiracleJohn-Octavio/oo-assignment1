package model.insect.beetles;

import model.board.Coordinate;
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
    public ArrayList<Coordinate> attack() {
        // TODO

        return null;
    }
}
