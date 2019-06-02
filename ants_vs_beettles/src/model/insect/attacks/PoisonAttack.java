package model.insect.attacks;

import model.game_engine.GameEngine;
import model.insect.Insect;

// Decorator pattern
public class PoisonAttack extends Attack {
    public PoisonAttack() {
        super();
    }

    public PoisonAttack(Attack additionalAttack) {
        super(additionalAttack);
    }

    @Override
    public void attack(GameEngine gameEngine, Insect attacker, Insect attackee) {
        super.attack(gameEngine, attacker, attackee);

        attackee.setParalysis(3);
    }
}
