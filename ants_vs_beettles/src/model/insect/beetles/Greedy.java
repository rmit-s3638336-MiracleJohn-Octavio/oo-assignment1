package model.insect.beetles;

import model.board.Coordinate;
import model.insect.Profile;

import java.util.ArrayList;

public class Greedy extends Beetle {
    private static Profile profile = new Profile(0,0,0, 0);

    public Greedy() {
        super(profile);
    }

    @Override
    public String toString() {
        return "g";
    }

    @Override
    public ArrayList<Coordinate> attack() {
        // TODO

        return null;
    }
}
