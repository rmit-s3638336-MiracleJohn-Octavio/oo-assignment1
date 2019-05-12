package model.insect.attacks;

import model.board.Tile;
import model.insect.Insect;

public class KickBackAttack extends HPAttack {

    private Tile newTile;

    public KickBackAttack(int damage, Tile newTile){
        super(damage);
        this.newTile = newTile;
    }

    @Override
    public void attack(Insect insect) {
        super.attack(insect);
        insect.getTile().resetInsect();
        insect.setTile(newTile);
        newTile.setInsect(insect);
    }
}
