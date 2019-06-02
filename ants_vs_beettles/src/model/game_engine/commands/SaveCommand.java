package model.game_engine.commands;

import model.game_engine.Caretaker;
import model.game_engine.GameEngine;

// Command pattern - Concrete Command
public class SaveCommand extends Command {
    public SaveCommand(Caretaker caretaker) {
        super(caretaker);
    }

    @Override
    public void execute(GameEngine gameEngine) {
        getCaretaker().save(gameEngine);
    }
}
