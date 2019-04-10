package model.insect.ants;

import model.board.Coordinate;
import model.insect.Insect;
import model.insect.Profile;

import java.util.ArrayList;

public abstract class Ant extends Insect {
    public Ant(Profile profile) {
        super(profile);
    }

    @Override
    public ArrayList<Coordinate> move() {
        // TODO

        return null;
    }
}
