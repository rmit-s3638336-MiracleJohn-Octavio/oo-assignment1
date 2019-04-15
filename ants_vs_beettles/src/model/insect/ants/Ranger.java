package model.insect.ants;

import model.board.Tile;
import model.insect.Profile;

import java.util.ArrayList;

public class Ranger extends Ant {
    private static Profile profile = new Profile(0,0,0, 0);

    public Ranger() {
        super(profile);
    }

    @Override
    public String toString() {
        return "r";
    }
    
    @Override
    public String getFullName() {
    	return "ranger";
    }

    @Override
    public ArrayList<Tile> getValidAttackTiles() {
        return null;
    }
}
