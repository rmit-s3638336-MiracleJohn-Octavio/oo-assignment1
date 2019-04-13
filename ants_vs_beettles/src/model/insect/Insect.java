package model.insect;

import model.board.Board;
import model.board.Tile;

import java.util.ArrayList;

public abstract class Insect {
    private int healthPoints;
    private Profile profile;
    private Tile tile;

    public Insect(Profile profile) {
        this.profile = profile;
        healthPoints = profile.getMaxHealthPoints();
    }

    public void decreaseHealthPoints(int damage) {
        healthPoints -= damage;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public abstract ArrayList<Tile> getValidMoveTiles(Board board);
}
