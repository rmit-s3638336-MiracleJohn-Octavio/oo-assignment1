package model.insect.ants;

import model.board.valid_tiles_gen.ValidAirAttackTilesGenerator;
import model.insect.Insect;
import model.insect.Profile;
import model.insect.attacks.HPAttack;

public class Ranger extends Ant {
    private static final int MAX_HEALTH_POINTS = 9;
    private static final int DAMAGE = 4;
    private static final int MOVE_RANGE = 3;
    private static final int ATTACK_RANGE = 3;
    private static final Profile profile = new Profile(MAX_HEALTH_POINTS, DAMAGE, MOVE_RANGE, ATTACK_RANGE);

    public Ranger() {
        super(profile, new ValidAirAttackTilesGenerator(), new HPAttack());
    }

    private Ranger (Ranger ranger){
        super(ranger);
    }

    @Override
    public String toString() {
        return "r";
    }
    
    @Override
    public String getFullName() {
    	return "ranger";
    }

    @Override
    public Insect mementoClone() {
        return new Ranger(this);
    }
}
