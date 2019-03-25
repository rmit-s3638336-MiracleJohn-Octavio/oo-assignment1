package model.insect.ants;

import model.board.Coordinate;
import model.insect.Insect;
import model.insect.Profile;

public class Ranger extends Insect {
    private static Profile profile = new Profile(0,0,0, 0);

    public Ranger(Coordinate coordinate) {
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


    public static Profile getProfile() {
        return profile;
    }

    public static void setProfile(Profile profile) {
        Ranger.profile = profile;
    }
}
