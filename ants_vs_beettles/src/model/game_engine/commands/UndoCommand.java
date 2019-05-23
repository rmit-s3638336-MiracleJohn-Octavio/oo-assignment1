package model.game_engine.commands;

import model.game_engine.Caretaker;
import model.game_engine.GameEngine;

public class UndoCommand implements Command {
    public static final int UNDOS_PER_COMMAND = 2;
    public static final int UNDO_LIMIT = 3;

    private Caretaker caretaker;

    public UndoCommand(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    @Override
    public void execute(GameEngine gameEngine) {
        for (int i = 0; i < UNDOS_PER_COMMAND; i++) {
            caretaker.undo(gameEngine);
        }

        gameEngine.updateViews();
    }
}
