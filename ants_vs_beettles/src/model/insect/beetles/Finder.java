package model.insect.beetles;

import model.board.valid_tiles_gen.ValidAirAttackTilesGenerator;
import model.insect.Insect;
import model.insect.Profile;
import model.insect.attacks.KickBackAttack;

public class Finder extends Beetle {
    private static final int MAX_HEALTH_POINTS = 7;
    private static final int DAMAGE = 1;
    private static final int MOVE_RANGE = 4;
    private static final int ATTACK_RANGE = 2;
    private static final Profile profile = new Profile(MAX_HEALTH_POINTS, DAMAGE, MOVE_RANGE, ATTACK_RANGE);

    public Finder() {
        super(profile, new ValidAirAttackTilesGenerator(), new KickBackAttack());
    }

    public Finder(Finder finder) {
        super(finder.getProfile(), new ValidAirAttackTilesGenerator(), new KickBackAttack());
    }

    @Override
    public String toString() {
        return "f";
    }

    @Override
    public String getFullName() {
        return "finder";
    }

    @Override
    public Insect cloneInsect() {
        return new Finder(this);
    }
}
