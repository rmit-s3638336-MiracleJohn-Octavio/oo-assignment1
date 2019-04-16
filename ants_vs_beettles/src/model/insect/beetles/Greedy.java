package model.insect.beetles;

import model.board.Tile;
import model.insect.Profile;

import java.util.ArrayList;

public class Greedy extends Beetle {
    private static Profile profile = new Profile(0,0,2, 0);

    public Greedy() {
        super(profile);
    }

    @Override
    public String toString() {
        return "g";
    }
    
    @Override
    public String getFullName() {
    	return "greedy";
    }

    @Override
    public ArrayList<Tile> getValidAttackTiles() {
        return null;
    }
}
