package model.insect;

public abstract class Insect {

    private Profile profile;
    private Coordinate coordinate;

    public Insect(Profile profile, Coordinate coordinate) {
        this.profile = profile;
        this.coordinate = coordinate;
    }

    public abstract boolean move();

    public abstract boolean attack();

}
