package model.insect.beetles;

import model.insect.Insect;
import model.insect.Profile;

public abstract class Beetle extends Insect {
    public Beetle(Profile profile) {
        super(profile);
    }

    public void fly() {
        // TODO
    }

    public abstract void airAttack();
}
