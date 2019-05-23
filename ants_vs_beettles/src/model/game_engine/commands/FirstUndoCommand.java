package model.game_engine.commands;

import model.game_engine.Caretaker;
import model.game_engine.GameEngine;

public class FirstUndoCommand extends UndoCommand {
    public FirstUndoCommand(Caretaker caretaker) {
        super(caretaker);
    }

    @Override
    public void execute(GameEngine gameEngine) {
        super.execute(gameEngine);
        super.getCaretaker().undo(gameEngine);
    }
}
