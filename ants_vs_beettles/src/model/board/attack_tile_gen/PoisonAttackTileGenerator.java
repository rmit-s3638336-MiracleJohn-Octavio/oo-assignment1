package model.board.attack_tiles_gen;

import model.board.AttackTile;
import model.board.Tile;
import model.board.attacks.Attack;
import model.board.attacks.PoisonAttack;
import model.insect.Insect;

// TODO: extends HPAttackTileGen???
public class PoisonAttackTileGenerator implements AttackTileGenerator {
    @Override
    public Tile getAttackTile(Insect insect, Tile tile) {
        int damage = insect.getProfile().getDamage();
        Attack attack = new PoisonAttack(damage);
        return new AttackTile(tile.getX(), tile.getY(), attack);
    }
}
