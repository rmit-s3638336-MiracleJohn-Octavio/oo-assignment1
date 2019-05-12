package model.board.attacks;

import model.insect.Insect;

public class PoisonAttack extends HPAttack {

    public PoisonAttack(int damage){

        super(damage);
    }

    @Override
    public void attack(Insect insect) {
        super.attack(insect);
        insect.setParalysed(true);
    }
}
