package model.insect.ants;

import model.insect.Insect;

public class Ranger extends Insect {
    private static final int HEALTH_POINTS = 0;
    private static final int DAMAGE = 0;
    private static final int SPEED = 0;
    private static final int ATTACK_RANGE = 0;

    public Ranger() {
        super(HEALTH_POINTS, DAMAGE, SPEED, ATTACK_RANGE);
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public boolean attack() {
        return false;
    }
}
