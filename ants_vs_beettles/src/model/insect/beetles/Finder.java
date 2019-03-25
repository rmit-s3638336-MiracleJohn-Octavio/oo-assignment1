package model.insect.beetles;

import model.board.Coordinate;
import model.insect.Insect;
import model.insect.Profile;

public class Finder extends Insect {
    private static Profile profile = new Profile(0,0,0, 0);

    public Finder(Coordinate coordinate) {
        super(profile, coordinate);
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
        Finder.profile = profile;
    }

}
