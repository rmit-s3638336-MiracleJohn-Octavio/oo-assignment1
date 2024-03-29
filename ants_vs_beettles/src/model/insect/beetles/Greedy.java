package model.insect.beetles;

import model.board.valid_tiles_gen.ValidAirAttackTilesGenerator;
import model.insect.Insect;
import model.insect.Profile;
import model.insect.attacks.HPAttack;

public class Greedy extends Beetle {
    private static final int MAX_HEALTH_POINTS = 15;
    private static final int DAMAGE = 5;
    private static final int MOVE_RANGE = 2;
    private static final int ATTACK_RANGE = 2;
    private static final int HEAL_POINTS = 1;

    private static final Profile profile = new Profile(MAX_HEALTH_POINTS, DAMAGE, MOVE_RANGE, ATTACK_RANGE, HEAL_POINTS);

    public Greedy() {
        super(profile, new ValidAirAttackTilesGenerator(), new HPAttack());
    }

    public Greedy(Greedy greedy) {
        super(greedy);
    }

    @Override
    public String toString() {
        return "g";
    }

    @Override
    public String getFullName() {
        return "greedy";
    }

    @Override
    public Insect mementoClone() {
        return new Greedy(this);
    }
}
