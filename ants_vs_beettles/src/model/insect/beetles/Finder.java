package model.insect.beetles;

import model.insect.Profile;

public class Finder extends Beetle {
    private static Profile profile = new Profile(0,0,0, 0);

    public Finder() {
        super(profile);
    }

    @Override
    public void airAttack() {
        // TODO
    }

    @Override
    public String toString() {
        return "f";
    }
}
