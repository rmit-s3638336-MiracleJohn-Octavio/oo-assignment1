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
    private int id;
    private int healthPoints;
    private Profile profile;
    private Tile tile;
    private int paralysed;

    private ValidPlaceTilesGenerator validPlaceTilesGenerator;
    private ValidTilesGenerator validMoveTilesGenerator;
    private ValidTilesGenerator validAttackTilesGenerator;
    private Attack attack;

    public Insect(Profile profile, ValidTilesGenerator validMoveTilesGenerator,
                  ValidTilesGenerator validAttackTilesGenerator, Attack attack) {
        this.profile = profile;
        healthPoints = profile.getMaxHealthPoints();
        paralysed = 0;

        validPlaceTilesGenerator = new ValidPlaceTilesGenerator();
        this.validMoveTilesGenerator = validMoveTilesGenerator;
        this.validAttackTilesGenerator = validAttackTilesGenerator;
        this.attack = attack;
    }

    public Insect(Insect insect){
        profile = insect.profile;
        healthPoints = profile.getMaxHealthPoints();
        paralysed = 0;

        validPlaceTilesGenerator = new ValidPlaceTilesGenerator();
        validMoveTilesGenerator = insect.validMoveTilesGenerator;
        validAttackTilesGenerator = insect.validAttackTilesGenerator;
        attack = insect.attack;
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

    public void paralyse() {
        paralysed = 2;
    }

    public boolean isParalysed() {
        return paralysed > 0;
    }

    public void deParalyse() {
        if (paralysed > 0) {
            paralysed--;
        }
    }

    public void initInsect(int id, Tile tile) {
        this.id = id;
        this.tile = tile;
    }

    public ValidPlaceTilesGenerator getValidPlaceTilesGenerator() {
        return validPlaceTilesGenerator;
    }

    public abstract List<Tile> getValidPlaceTiles(Board board);

    public List<Tile> getValidMoveTiles(Board board) {
        return getValidTiles(board, validMoveTilesGenerator, this.getProfile().getMoveRange());
    }

    public List<Tile> getValidAttackTiles(Board board) {
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

    public abstract Insect cloneInsect();
}
