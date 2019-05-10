package model.insect.attacks;

import model.insect.Insect;

public class KickBackAttack extends HPAttack {

    private int x,y;

    public KickBackAttack(int damage, int x, int y){
        super(damage);
        this.x = x;
        this.y = y;
    }

    @Override
    public void attack(Insect insect) {
        super.attack(insect);

    }
}
