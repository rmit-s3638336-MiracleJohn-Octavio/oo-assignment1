package model.player;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import model.insect.Insect;

@Invariant("insectCount >= 0 && undoCount >= 0")
public class Player {
    private Class insectClass;
    private final int INSECT_LIMIT;
    private int insectCount;

    private boolean undoable;
    private final int UNDO_LIMIT = 3;
    private int undoCount;

    private Target target;

    public Player(String insectClassName, Target target, int numOfInsects) {
        try {
            insectClass = Class.forName("model.insect." + insectClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        INSECT_LIMIT = numOfInsects;
        insectCount = 0;

        undoable = true;
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

    @Ensures("undoable == false")
    public void switchOffUndo() {
        undoable = false;
    }

    public boolean ownInsect(Insect insect) {
        return insectClass.isInstance(insect);
    }

    @Ensures("insectCount == old(insectCount) + 1")
    public void addInsect() {
        insectCount++;
    }

    @Ensures("insectCount == old(insectCount) - 1")
    public void removeInsect() {
        insectCount--;
    }

    public Target getTarget() {
        return target;
    }

    public String getTeamName() {
        return insectClass.getSimpleName() + "s";
    }
}
