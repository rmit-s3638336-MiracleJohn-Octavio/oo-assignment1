package model.insect.attacks;

import model.board.Tile;
import model.game_engine.GameEngine;
import model.insect.Insect;

// Decorator pattern
public class KickBackAttack extends Attack {
    public KickBackAttack() {
        super();
    }

    public KickBackAttack(Attack additionalAttack) {
        super(additionalAttack);
    }

    @Override
    public void attack(GameEngine gameEngine, Insect attacker, Insect attackee) {
        super.attack(gameEngine, attacker, attackee);

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

        Tile newAttackeeTile = gameEngine.getBoard().getTile(newAttackeeX, newAttackeeY);
        // Push the attackee to this tile; if there's an insect on this tile, it'll be killed
        if (newAttackeeTile != null) {
            attackee.getTile().resetInsect();
            newAttackeeTile.setInsect(attackee);
        }
    }
}
