package model.insect.ants;

import model.board.AttackTile;
import model.board.Board;
import model.board.Tile;
import model.insect.Insect;
import model.insect.Profile;
import model.insect.attacks.Attack;
import model.insect.attacks.HPAttack;

import java.util.ArrayList;

public class Ranger extends Ant {
    private static Profile profile = new Profile(9,4,3, 3);

    public Ranger() {
        super(profile);
    }

    public Ranger (Ranger ranger){
        super(ranger.profile);
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
                Attack attack = new HPAttack(this.getProfile().getDamage());
                Tile attTile = new AttackTile(x, y, attack);
                validTiles.add(attTile);
            }
        }

        return validTiles;
    }

    @Override
    public Insect cloneInsect() {
        return new Ranger(this);
    }
}
