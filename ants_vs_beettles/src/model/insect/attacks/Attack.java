package model.insect.attacks;

import model.game_engine.GameEngine;
import model.insect.Insect;

// Decorator pattern
public abstract class Attack {
    private Attack additionalAttack;

    public Attack() {

    }

    public Attack(Attack additionalAttack) {
        this.additionalAttack = additionalAttack;
    }

    public void attack(GameEngine gameEngine, Insect attacker, Insect attackee) {
        if (additionalAttack != null) {
            additionalAttack.attack(gameEngine, attacker, attackee);
        }
    }

}
