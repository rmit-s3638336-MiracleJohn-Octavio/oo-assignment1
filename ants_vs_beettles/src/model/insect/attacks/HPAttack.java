package model.insect.attacks;

import model.insect.Insect;

public class HPAttack extends Attack{

    private int damage;

    public HPAttack(int damage){

        this.damage = damage;
    }

    @Override
    public void attack(Insect insect) {
        insect.decreaseHealthPoints(damage);
    }
}
