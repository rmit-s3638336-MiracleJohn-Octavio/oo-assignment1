package model.insect.ants;

import model.board.Coordinate;
import model.insect.Profile;

import java.util.ArrayList;

public class Heavy extends Ant {
    private static Profile profile = new Profile(0, 0, 0, 0);

    public Heavy() {
        super(profile);
    }

    @Override
    public String toString() {
        return "h";
    }

    @Override
    public ArrayList<Coordinate> attack() {
        // TODO

        return null;
    }
}
