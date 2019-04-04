package model.insect.ants;

import model.insect.Profile;

public class Scout extends Ant {
    private static Profile profile = new Profile(0, 0, 0, 0);

    public Scout() {
        super(profile);
    }

    @Override
    public String toString() {
        return "s";
    }
}