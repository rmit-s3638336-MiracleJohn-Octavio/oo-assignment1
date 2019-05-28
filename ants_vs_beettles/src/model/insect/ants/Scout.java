package model.insect.ants;

import model.board.valid_tiles_gen.ValidGroundAttackTilesGenerator;
import model.insect.Insect;
import model.insect.Profile;
import model.insect.TargetSearcher;
import model.insect.attacks.HPAttack;

public class Scout extends Ant implements TargetSearcher {
    private static final int MAX_HEALTH_POINTS = 7;
    private static final int DAMAGE = 2;
    private static final int MOVE_RANGE = 4;
    private static final int ATTACK_RANGE = 2;
    // TODO: static profile, generator and attack???
    private static final int HEAL_POINTS = 2;

    private static final Profile profile = new Profile(MAX_HEALTH_POINTS, DAMAGE, MOVE_RANGE, ATTACK_RANGE, HEAL_POINTS);

    public Scout() {
        super(profile, new ValidGroundAttackTilesGenerator(), new HPAttack());
    }

    private Scout (Scout scout){
        super(scout);
    }

    @Override
    public String toString() {
        return "s";
    }
    
    @Override
    public String getFullName() {
    	return "scout";
    }

    @Override
    public Insect mementoClone() {
        return new Scout(this);
    }
}