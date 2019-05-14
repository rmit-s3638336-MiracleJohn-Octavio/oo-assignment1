package model.board.attack_tiles_gen;

import model.board.AttackTile;
import model.board.Tile;
import model.board.attacks.Attack;
import model.board.attacks.HPAttack;
import model.insect.Insect;

public class HPAttackTileGenerator implements AttackTileGenerator {
    @Override
    public Tile getAttackTile(Insect insect, Tile tile) {
        int damage = insect.getProfile().getDamage();
        Attack attack = new HPAttack(damage);
        return new AttackTile(tile.getX(), tile.getY(), attack);
    }
}
