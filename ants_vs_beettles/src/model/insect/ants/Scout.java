package model.insect.ants;

import model.board.Tile;
import model.insect.Profile;

import java.util.ArrayList;

public class Scout extends Ant {
    private static Profile profile = new Profile(0, 0, 4, 0);

    public Scout() {
        super(profile);
    }

    @Override
    public String toString() {
        return "s";
    }
    
    @Override
    public String getFullName() {
    	return "scout";
    }

    @Override
    public ArrayList<Tile> getValidAttackTiles() {
        return null;
    }
}