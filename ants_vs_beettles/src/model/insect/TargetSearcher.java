package model.insect;

import model.target.Target;

public interface TargetSearcher {
    public default boolean foundTarget(Insect insect, Target target) {
        return insect.getTile().equals(target.getTile());
    }
}
