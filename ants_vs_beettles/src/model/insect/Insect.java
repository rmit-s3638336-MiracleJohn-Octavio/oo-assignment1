package model.insect;

import model.board.Coordinate;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Insect {
    private int healthPoints;
    private Profile profile;
    private Coordinate coordinate;

    public Insect(Profile profile) {
        this.profile = profile;
        healthPoints = profile.getMaxHealthPoints();
    }

    public void decreaseHealthPoints(int damage) {
        healthPoints -= damage;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public abstract ArrayList<Coordinate> move();

    public abstract ArrayList<Coordinate> attack();
}
