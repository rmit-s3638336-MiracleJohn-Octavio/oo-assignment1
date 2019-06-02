package model.game_engine.commands;

import model.game_engine.Caretaker;
import model.game_engine.GameEngine;

// Command Pattern - Concrete Command
public class UndoCommand extends Command {
    public UndoCommand(Caretaker caretaker) {
        super(caretaker);
    }

    @Override
    public void execute(GameEngine gameEngine) {
        final int UNDOS_PER_COMMAND = 2;
        for (int i = 0; i < UNDOS_PER_COMMAND; i++) {
            getCaretaker().undo(gameEngine);
        }
    }
}
