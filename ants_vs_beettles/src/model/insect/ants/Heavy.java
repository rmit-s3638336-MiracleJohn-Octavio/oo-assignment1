package model.insect.ants;

import model.board.Tile;
import model.insect.Insect;
import model.insect.Profile;

import java.util.ArrayList;

public class Heavy extends Ant {
    private static Profile profile = new Profile(0, 0, 2, 0);

    public Heavy() {
        super(profile);
    }

    public Heavy (Heavy heavy){
        super(heavy.profile);
    }

    @Override
    public String toString() {
        return "h";
    }
    
    @Override
    public String getFullName() {
    	return "heavy";
    }

    @Override
    public ArrayList<Tile> getValidAttackTiles() {
        return null;
    }

    @Override
    public Insect cloneInsect() {
        return new Heavy(this);
    }
}
