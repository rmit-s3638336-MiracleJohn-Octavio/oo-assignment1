package model.insect.beetles;

import model.board.Coordinate;
import model.insect.Insect;
import model.insect.Profile;

import java.util.ArrayList;

public abstract class Beetle extends Insect {
    public Beetle(Profile profile) {
        super(profile);
    }

    @Override
    public ArrayList<Coordinate> move() {
        // TODO

        return null;
    }
}
