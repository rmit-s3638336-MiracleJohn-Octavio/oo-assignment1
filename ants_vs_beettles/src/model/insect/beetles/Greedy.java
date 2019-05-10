package model.insect.beetles;

import model.board.Tile;
import model.insect.Insect;
import model.insect.Profile;


import java.util.ArrayList;

public class Greedy extends Beetle {
    private static Profile profile = new Profile(0,0,2, 0);

    public Greedy() {
        super(profile);
    }

    public Greedy (Greedy greedy){
        super(greedy.profile);
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

    @Override
    public Insect cloneInsect() {
        return new Greedy(this);
    }
}
