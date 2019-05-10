package model.insect.beetles;

import model.board.Tile;
import model.insect.Insect;
import model.insect.Profile;

import java.util.ArrayList;

public class Finder extends Beetle {
    private static Profile profile = new Profile(0,0,4, 0);

    public Finder() {
        super(profile);
    }

    public Finder (Finder finder){
        super(finder.profile);
    }
    @Override
    public String toString() {
        return "f";
    }
    
    @Override
    public String getFullName() {
    	return "finder";
    }

    @Override
    public ArrayList<Tile> getValidAttackTiles() {
        return null;
    }

    @Override
    public Insect cloneInsect() {
        return new Finder(this);
    }
}
