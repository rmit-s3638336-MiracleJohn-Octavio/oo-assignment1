package model.insect.beetles;

import model.board.AttackTile;
import model.board.Board;
import model.board.Tile;
import model.insect.Insect;
import model.insect.Profile;
import model.insect.attacks.Attack;
import model.insect.attacks.HPAttack;
import model.insect.attacks.KickBackAttack;

import java.util.ArrayList;

public class Finder extends Beetle {
    private static Profile profile = new Profile(7,1,4, 2);

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
                Attack attack;
                Tile newTile = board.getTile(x + xInc, y + yInc);
                if (newTile != null) {
                    attack = new KickBackAttack(this.getProfile().getDamage(), newTile);
                } else {
                    attack = new HPAttack(this.getProfile().getDamage());
                }

                Tile attTile = new AttackTile(x, y, attack);
                validTiles.add(attTile);
            }
        }

        return validTiles;
    }

    @Override
    public Insect cloneInsect() {
        return new Finder(this);
    }
}
