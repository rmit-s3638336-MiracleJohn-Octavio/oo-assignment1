package model.insect.beetles;

import model.board.Tile;
import model.insect.Insect;
import model.insect.Profile;


import java.util.ArrayList;

public class Bogus extends Beetle {
    private static Profile profile = new Profile(0,0,3, 0);

    public Bogus() {
        super(profile);
    }

    public Bogus (Bogus bogus){
        super(bogus.profile);
    }
    @Override
    public String toString() {
        return "b";
    }
    
    @Override
    public String getFullName() {
    	return "bogus";
    }

    @Override
    public ArrayList<Tile> getValidAttackTiles() {
        return null;
    }

    @Override
    public Insect cloneInsect() {
        return new Bogus(this);
    }
}