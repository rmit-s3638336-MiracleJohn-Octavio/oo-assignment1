package model.player;

import com.google.java.contract.Invariant;
import model.game_engine.commands.UndoCommand;

@Invariant("insectLimit >= 0 && undoLimit >= 0")
public class Player {
    // TODO: tobe specified by the user
    private int insectLimit = 10;
    private boolean undo = true;
    private int undoLimit = UndoCommand.UNDO_LIMIT;

    public Player() {

    }

    public boolean reachedMaxInsects() {
        return insectLimit == 0;
    }

    public boolean reachedUndoLimit() {
        if (undoLimit > 0) {
            undoLimit--;
            return false;
        }

        return true;
    }

    public void addInsect() {
        insectLimit--;
    }

    public void removeInsect() {
        insectLimit++;
    }
}
