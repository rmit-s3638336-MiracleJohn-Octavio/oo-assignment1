package model.board;

import model.board.attacks.Attack;

public class AttackTile extends Tile{
    private Attack attack;

    public AttackTile(int x, int y, Attack attack) {
        super(x, y);
        this.attack = attack;
    }

    public Attack getAttack() {
        return attack;
    }
}
