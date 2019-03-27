package model.insect;

import model.board.Coordinate;

public abstract class Insect {

    private Profile profile;
    private Coordinate coordinate;

    public Insect(Profile profile) {
        this.profile = profile;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public abstract boolean move();

    public abstract boolean attack();
}
