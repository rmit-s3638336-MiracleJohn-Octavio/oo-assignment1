package model.insect.ants;

import model.insect.Profile;

public class Ranger extends Ant {
    private static Profile profile = new Profile(0,0,0, 0);

    public Ranger() {
        super(profile);
    }

    @Override
    public String toString() {
        return "r";
    }
}
