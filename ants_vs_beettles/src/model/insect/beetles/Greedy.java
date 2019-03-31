package model.insect.beetles;

import model.board.Coordinate;
import model.insect.Insect;
import model.insect.Profile;

public class Greedy extends Insect {
    private static Profile profile = new Profile(0,0,0, 0);

    public Greedy() {
        super(profile);
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public boolean attack() {
        return false;
    }

    public static void setProfile(Profile profile) {
        Greedy.profile = profile;
    }
}
