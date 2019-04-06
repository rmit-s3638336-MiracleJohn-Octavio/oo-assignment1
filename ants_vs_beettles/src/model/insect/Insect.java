package model.insect;

import model.board.*;

public abstract class Insect {
    private int healthPoints;
    private Profile profile;

    public Insect(Profile profile) {
        this.profile = profile;
        healthPoints = profile.getMaxHealthPoints();
    }

    public void decreaseHealthPoints(int damage) {
        healthPoints -= damage;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
