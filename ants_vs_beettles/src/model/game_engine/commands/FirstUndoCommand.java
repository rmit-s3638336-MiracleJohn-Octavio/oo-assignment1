package model.game_engine.commands;

import model.game_engine.Caretaker;
import model.game_engine.GameEngine;

// Command pattern - Concrete Command
public class FirstUndoCommand extends UndoCommand {
    public FirstUndoCommand(Caretaker caretaker) {
        super(caretaker);
    }

    @Override
    public void execute(GameEngine gameEngine) {
        super.execute(gameEngine);
        getCaretaker().undo(gameEngine);
    }
}
