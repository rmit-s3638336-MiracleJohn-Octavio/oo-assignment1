package model.insect.beetles;

import model.board.Coordinate;
import model.insect.Insect;
import model.insect.Profile;

public class Bogus extends Insect {
    private static Profile profile = new Profile(0,0,0, 0);

    public Bogus() {
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

    public static Profile getProfile() {
        return profile;
    }

    public static void setProfile(Profile profile) {
        Bogus.profile = profile;
    }
}