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
        caretaker.undo(gameEngine);
        caretaker.undo(gameEngine);
        caretaker.undo(gameEngine);
        gameEngine.updateViews();
    }
}
