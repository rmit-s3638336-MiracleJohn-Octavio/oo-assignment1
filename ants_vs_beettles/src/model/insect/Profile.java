package model.insect;

public class Profile {
    private int maxHealthPoints;
    private int damage;
    private int speed;
    private int attackRange;

    public Profile(int maxHealthPoints, int damage, int speed, int attackRange) {
        this.maxHealthPoints = maxHealthPoints;
        this.damage = damage;
        this.speed = speed;
        this.attackRange = attackRange;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
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