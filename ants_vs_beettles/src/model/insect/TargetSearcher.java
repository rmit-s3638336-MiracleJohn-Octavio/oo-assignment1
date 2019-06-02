package model.insect;

import model.player.Target;

public interface TargetSearcher {
    public default boolean foundTarget(Insect insect, Target target) {
        return insect.getTile().equals(target.getTile());
    }
}
