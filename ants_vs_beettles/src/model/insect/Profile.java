package model.insect;

// Single Responsibility Principle
// GRASP - High Cohesion
public class Profile {
    private int maxHealthPoints;
    private int damage;
    private int moveRange;
    private int attackRange;

    public Profile(int maxHealthPoints, int damage, int moveRange, int attackRange) {
        this.maxHealthPoints = maxHealthPoints;
        this.damage = damage;
        this.moveRange = moveRange;
        this.attackRange = attackRange;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public int getDamage() {
        return damage;
    }

    public int getMoveRange() {
        return moveRange;
    }

    public int getAttackRange() {
        return attackRange;
    }
}