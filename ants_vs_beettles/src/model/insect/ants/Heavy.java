package model.insect.ants;

import model.board.Coordinate;
import model.insect.Insect;
import model.insect.Profile;

public class Heavy extends Insect {
    private static Profile profile = new Profile(0, 0, 0, 0);

    public Heavy(Profile profile) {
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
        Heavy.profile = profile;
    }
}
