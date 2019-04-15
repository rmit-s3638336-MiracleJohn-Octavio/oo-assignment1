package model.insect;

import model.board.Board;
import model.board.Tile;

import java.util.ArrayList;

import javafx.scene.layout.Pane;

public abstract class Insect {
    private int healthPoints;
    private Profile profile;
    private Tile tile;
    private Pane tileView;

    public Insect(Profile profile) {
        this.profile = profile;
        healthPoints = profile.getMaxHealthPoints();
    }
     
    public abstract String getFullName(); 

    public void decreaseHealthPoints(int damage) {
        healthPoints -= damage;
    }

    public Pane getTileView() {
		return tileView;
	}

	public void setTileView(Pane tileView) {
		this.tileView = tileView;
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

    public abstract ArrayList<Tile> getValidMoveTiles(int x, int y, int xInc, int yInc, int range, Board board);

    public abstract ArrayList<Tile> getValidAttackTiles();
}
