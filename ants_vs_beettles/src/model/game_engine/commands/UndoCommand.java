package model.game_engine.commands;

import model.game_engine.Caretaker;
import model.game_engine.GameEngine;

public class UndoCommand implements Command {
    private Caretaker caretaker;

    public UndoCommand(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    @Override
    public void execute(GameEngine gameEngine) {
        final int UNDOS_PER_COMMAND = 2;
        for (int i = 0; i < UNDOS_PER_COMMAND; i++) {
            caretaker.undo(gameEngine);
        }
    }

    public Caretaker getCaretaker() {
        return caretaker;
    }
}
