package model.insect.attacks;

import model.board.Board;
import model.board.Tile;
import model.insect.Insect;
import model.player.Player;

public class KickBackAttack extends HPAttack {
    @Override
    public void attack(Insect attacker, Board board, Player player, Insect attackee) {
        super.attack(attacker, board, player, attackee);
        if (!attackee.killed()) {
            int attackerX = attacker.getTile().getX();
            int attackerY = attacker.getTile().getY();
            int attackeeX = attackee.getTile().getX();
            int attackeeY = attackee.getTile().getY();

            int newAttackeeX = attackeeX;
            int newAttackeeY = attackeeY;

            if (attackerX == attackeeX) {
                // The attacker and attackee are standing on the same row
                newAttackeeY = attackeeY + ((attackeeY - attackerY > 0) ? 1 : -1);
            } else {
                // The attacker and attackee are standing on the same col
                newAttackeeX = attackeeX + ((attackeeX - attackerX > 0) ? 1 : -1);
            }

            Tile newAttackeeTile = board.getTile(newAttackeeX, newAttackeeY);
            if (newAttackeeTile != null) {
                attackee.getTile().resetInsect();
                attackee.setTile(newAttackeeTile);
                newAttackeeTile.setInsect(attackee);
            }
        }
    }
}
