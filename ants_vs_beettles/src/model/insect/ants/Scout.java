package model.insect.ants;

import model.board.Tile;
import model.insect.Insect;
import model.insect.Profile;

import java.util.ArrayList;

public class Scout extends Ant {
    private static Profile profile = new Profile(0, 0, 4, 0);

    public Scout() {
        super(profile);
    }

    public Scout (Scout scout){
        super(scout.profile);
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

    @Override
    public Insect cloneInsect() {
        return new Scout(this);
    }
}