package model.game_engine.commands;

import model.game_engine.Caretaker;
import model.game_engine.GameEngine;

public class SaveCommand implements Command {
    private Caretaker caretaker;

    public SaveCommand(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    @Override
    public void execute(GameEngine gameEngine) {
        caretaker.save(gameEngine);
    }
}
