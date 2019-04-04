package model.insect.ants;

import model.insect.Profile;

public class Heavy extends Ant {
    private static Profile profile = new Profile(0, 0, 0, 0);

    public Heavy() {
        super(profile);
    }

    @Override
    public String toString() {
        return "h";
    }
}
