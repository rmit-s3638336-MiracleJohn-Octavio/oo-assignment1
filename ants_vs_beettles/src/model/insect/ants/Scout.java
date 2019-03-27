package model.insect.ants;

import model.board.Coordinate;
import model.insect.Insect;
import model.insect.Profile;

public class Scout extends Insect {
    private static Profile profile = new Profile(0, 0, 0, 0);

    public Scout() {
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
        Scout.profile = profile;
    }

    @Override
    public String toString() {
        return "s";
    }
}