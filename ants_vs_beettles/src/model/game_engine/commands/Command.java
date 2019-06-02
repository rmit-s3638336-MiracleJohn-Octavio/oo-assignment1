package model.game_engine.commands;

import model.game_engine.Caretaker;
import model.game_engine.GameEngine;

// Command pattern - abstract Command
public abstract class Command {
    private Caretaker caretaker;

    public Command(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public abstract void execute(GameEngine gameEngine);
}
