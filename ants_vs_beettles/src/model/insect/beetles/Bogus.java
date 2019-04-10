package model.insect.beetles;

import model.board.Coordinate;
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
    public ArrayList<Coordinate> attack() {
        // TODO

        return null;
    }
}