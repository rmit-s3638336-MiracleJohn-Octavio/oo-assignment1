package model.insect.attacks;

import model.game_engine.GameEngine;
import model.insect.Insect;

// Decorator pattern
public class HPAttack extends Attack {
    public HPAttack() {
        super();
    }

    public HPAttack(Attack additionalAttack) {
        super(additionalAttack);
    }

    @Override
    public void attack(GameEngine gameEngine, Insect attacker, Insect attackee) {
        super.attack(gameEngine, attacker, attackee);

        int damage = attacker.getProfile().getDamage();
        attackee.decreaseHealthPoints(damage);

        if (attackee.killed()) {
            gameEngine.getBoard().getTile(attackee.getTile().getX(), attackee.getTile().getY()).resetInsect();
            gameEngine.getCurrentPlayer().removeInsect();
        }
    }
}
