package model.insect.attacks;

import model.board.Board;
import model.insect.Insect;
import model.player.Player;

public class HPAttack implements Attack{
    @Override
    public void attack(Insect attacker, Board board, Player player, Insect attackee) {
        int damage = attacker.getProfile().getDamage();
        attackee.decreaseHealthPoints(damage);

        if (attackee.killed()) {
            board.getTile(attackee.getTile().getX(), attackee.getTile().getY()).resetInsect();
            player.removeInsect(attackee.getId());
        }
    }
}
