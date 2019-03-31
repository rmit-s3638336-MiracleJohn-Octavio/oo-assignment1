package model.insect;

public class Profile {
    private int healthPoints;
    private int damage;
    private int speed;
    private int attackRange;

    public Profile(int healthPoints, int damage, int speed, int attackRange) {
        this.healthPoints = healthPoints;
        this.damage = damage;
        this.speed = speed;
        this.attackRange = attackRange;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }
}