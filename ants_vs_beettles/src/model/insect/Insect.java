package model.insect;

public abstract class Insect {

    private int healthPoints;
    private int damage;
    private int speed;
    private int attackRange;

    public Insect(int healthPoints, int damage, int speed, int attackRange) {
        this.healthPoints = healthPoints;
        this.damage = damage;
        this.speed = speed;
        this.attackRange = attackRange;
    }

    public abstract boolean move();

    public abstract boolean attack();

}
