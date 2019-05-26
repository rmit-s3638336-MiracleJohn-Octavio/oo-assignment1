package model.insect.ants;

import model.board.valid_tiles_gen.ValidGroundAttackTilesGenerator;
import model.insect.Insect;
import model.insect.Profile;
import model.insect.attacks.HPAttack;

public class Heavy extends Ant {
    private static final int MAX_HEALTH_POINTS = 15;
    private static final int DAMAGE = 6;
    private static final int MOVE_RANGE = 2;
    private static final int ATTACK_RANGE = 1;
    private static final int HEAL_POINTS = 1;

    private static final Profile profile = new Profile(MAX_HEALTH_POINTS, DAMAGE, MOVE_RANGE, ATTACK_RANGE, HEAL_POINTS);

    public Heavy() {
        super(profile, new ValidGroundAttackTilesGenerator(), new HPAttack());
    }

    private Heavy (Heavy heavy){
        super(heavy);
    }

    @Override
    public String toString() {
        return "h";
    }
    
    @Override
    public String getFullName() {
    	return "heavy";
    }

    @Override
    public Insect mementoClone() {
        return new Heavy(this);
    }
}
