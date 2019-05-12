package model.insect.beetles;

import model.board.AttackTile;
import model.board.Board;
import model.board.Tile;
import model.insect.Insect;
import model.insect.Profile;
import model.board.attacks.Attack;
import model.board.attacks.PoisonAttack;


import java.util.ArrayList;

public class Bogus extends Beetle {
    private static Profile profile = new Profile(9,3,3, 2);

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
    public ArrayList<Tile> getValidAttackTiles(int x, int y, int xInc, int yInc, int range, Board board) {
        ArrayList<Tile> validTiles = new ArrayList<>();
        Tile tile;

        for (int i = 1; i <= range; i++) {
            x += xInc;
            y += yInc;
            tile = board.getTile(x, y);

            // Stop when reach the bound of the board
            if (tile == null) {
                break;
            }

            if (tile.getInsect() != null) {
                Attack attack = new PoisonAttack(this.getProfile().getDamage());
                Tile attTile = new AttackTile(x, y, attack);
                validTiles.add(attTile);
            }
        }

        return validTiles;
    }

    @Override
    public Insect cloneInsect() {
        return new Bogus(this);
    }
}