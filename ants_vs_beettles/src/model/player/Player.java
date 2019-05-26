package model.player;

import com.google.java.contract.Invariant;
import model.target.Target;

@Invariant("insectCount >= 0 && undoCount >= 0")
public class Player {
    // TODO: tobe specified by the user
    private final int INSECT_LIMIT;
    private int insectCount;

    private boolean undoable;
    private final int UNDO_LIMIT;
    private int undoCount;

    private Target target;

    public Player(Target target) {
        INSECT_LIMIT = 10;
        insectCount = 0;

        undoable = true;
        UNDO_LIMIT = 3;
        undoCount = 0;

        this.target = target;
    }

    public boolean reachedMaxInsects() {
        return insectCount == INSECT_LIMIT;
    }

    public boolean undoable() {
        if (undoable && undoCount <  UNDO_LIMIT) {
            undoCount++;
            return true;
        }

        return false;
    }

    public boolean usedUndo() {
        return undoCount > 0;
    }

    public boolean firstUndo() {
        return undoCount == 1;
    }

    public void switchOffUndo() {
        undoable = false;
    }

    public void addInsect() {
        insectCount++;
    }

    public void removeInsect() {
        insectCount--;
    }

    public Target getTarget() {
        return target;
    }
}
