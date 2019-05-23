package model.insect.beetles;

import model.board.valid_tiles_gen.ValidAirAttackTilesGenerator;
import model.insect.Insect;
import model.insect.Profile;
import model.insect.attacks.PoisonAttack;

public class Bogus extends Beetle {
    private static final int MAX_HEALTH_POINTS = 9;
    private static final int DAMAGE = 3;
    private static final int MOVE_RANGE = 3;
    private static final int ATTACK_RANGE = 2;
    private static final Profile profile = new Profile(MAX_HEALTH_POINTS, DAMAGE, MOVE_RANGE, ATTACK_RANGE);

    public Bogus() {
        super(profile, new ValidAirAttackTilesGenerator(), new PoisonAttack());
    }

    public Bogus(Bogus bogus) {
        super(bogus);
    }

    @Override
    public String toString() {
        return "b";
    }

    @Override
    public String getFullName() {
        return "bogus";
    }

    @Override
    public Insect mementoClone() {
        return new Bogus(this);
    }
}