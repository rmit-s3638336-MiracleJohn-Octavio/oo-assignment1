package model.insect;

import model.board.Board;
import model.board.Tile;
import model.insect.attacks.Attack;
import model.board.valid_tiles_gen.ValidPlaceTilesGenerator;
import model.board.valid_tiles_gen.ValidTilesGenerator;
import model.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Single-Responsibility Principle
// Open-Closed Principle
// Liskov-Substitution Principle
// Dependency Inversion Principle
// GRASP - High Cohesion
// GRASP - Polymorphism
public abstract class Insect {
    private int healthPoints;
    private Profile profile;
    private Tile tile;
    private int paralysis;

    private ValidPlaceTilesGenerator validPlaceTilesGenerator;
    private ValidTilesGenerator validMoveTilesGenerator;
    private ValidTilesGenerator validAttackTilesGenerator;
    private Attack attack;

    public Insect(Profile profile, ValidTilesGenerator validMoveTilesGenerator,
                  ValidTilesGenerator validAttackTilesGenerator, Attack attack) {
        this.profile = profile;
        healthPoints = profile.getMaxHealthPoints();
        paralysis = 0;

        validPlaceTilesGenerator = new ValidPlaceTilesGenerator();
        this.validMoveTilesGenerator = validMoveTilesGenerator;
        this.validAttackTilesGenerator = validAttackTilesGenerator;
        this.attack = attack;
    }

    public Insect(Insect insect){
        profile = insect.profile;
        healthPoints = insect.healthPoints;
        paralysis = insect.paralysis;

        validPlaceTilesGenerator = new ValidPlaceTilesGenerator();
        validMoveTilesGenerator = insect.validMoveTilesGenerator;
        validAttackTilesGenerator = insect.validAttackTilesGenerator;
        attack = insect.attack;
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

    public void setParalysis(int paralysis) {
        this.paralysis = paralysis;
    }

    public boolean isParalysed() {
        return paralysis > 0;
    }

    public void deParalyse() {
        if (paralysis > 0) {
            paralysis--;
        }
    }

    protected ValidPlaceTilesGenerator getValidPlaceTilesGenerator() {
        return validPlaceTilesGenerator;
    }

    public abstract List<Tile> getValidPlaceTiles(Board board);

    public List<Tile> getValidActionTiles(Board board) {
        List<Tile> validTiles = new ArrayList<>();

        validTiles.addAll(getValidMoveTiles(board));
        validTiles.addAll(getValidAttackTiles(board));

        Collections.sort(validTiles);

        return validTiles;
    }

    private List<Tile> getValidMoveTiles(Board board) {
        return getValidTiles(board, validMoveTilesGenerator, this.getProfile().getMoveRange());
    }

    private List<Tile> getValidAttackTiles(Board board) {
        return getValidTiles(board, validAttackTilesGenerator, this.getProfile().getAttackRange());
    }

    private List<Tile> getValidTiles(Board board, ValidTilesGenerator generator, int range) {
        List<Tile> validTiles = new ArrayList<>();

        validTiles.addAll(generator.getValidTiles(this, board, 1, 0, range));
        validTiles.addAll(generator.getValidTiles(this, board, -1, 0, range));
        validTiles.addAll(generator.getValidTiles(this, board, 0, 1, range));
        validTiles.addAll(generator.getValidTiles(this, board, 0, -1, range));

        Collections.sort(validTiles);

        return validTiles;
    }

    public void attack(Board board, Player player, Insect attackee) {
        attack.attack(this, board, player, attackee);
    }

    public abstract Insect mementoClone();

    public Insect prototypeClone() {
        Insect insect = mementoClone();
        insect.healthPoints = insect.getProfile().getMaxHealthPoints();
        insect.paralysis = 0;

        return insect;
    }
}
