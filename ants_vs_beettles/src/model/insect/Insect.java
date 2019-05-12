package model.insect;

import model.board.Board;
import model.board.Tile;

import java.util.ArrayList;
import java.util.List;

import model.board.valid_tiles_gen.ValidPlaceTilesGenerator;

// Single-Responsibility Principle
// Open-Closed Principle
// Liskov-Substitution Principle
// Dependency Inversion Principle
// GRASP - High Cohesion
// GRASP - Polymorphism
public abstract class Insect {
    private int id;
    private int healthPoints;
    private Profile profile;
    private Tile tile;
    private boolean paralysed = false;

    private ValidPlaceTilesGenerator validPlaceTilesGenerator;

    public Insect(Profile profile) {
        this.profile = profile;
        healthPoints = profile.getMaxHealthPoints();
        validPlaceTilesGenerator = new ValidPlaceTilesGenerator();
    }

    public Insect(Insect insect){
        profile = insect.profile;
        healthPoints = profile.getMaxHealthPoints();
    }

    public int getId() {
        return id;
    }

    public abstract String getFullName();

    public boolean killed() {
        return healthPoints < 0;
    }

    public void decreaseHealthPoints(int damage) {
        healthPoints -= damage;
        System.out.println("HP after being attacked: " + healthPoints);
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

    public void setParalysed(boolean paralysed){
        this.paralysed = paralysed;
    }

    public boolean getParalysed() {
        return paralysed;
    }

    public void initInsect(int id, Tile tile) {
        this.id = id;
        this.tile = tile;
    }

    public List<Tile> getValidPlaceTiles(Board board) {
        return validPlaceTilesGenerator.getValidTiles(this, board);
    }

    public abstract ArrayList<Tile> getValidMoveTiles(int x, int y, int xInc, int yInc, int range, Board board);

    public abstract ArrayList<Tile> getValidAttackTiles(int x, int y, int xInc, int yInc, int range, Board board);

    public abstract Insect cloneInsect();
}

// get validAttackTile method: getting the valid attack tiles
//patterns: prototype, decorator,