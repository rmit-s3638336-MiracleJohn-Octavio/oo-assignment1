package model.game_engine.commands;

import model.game_engine.GameEngine;

public interface Command {
    public abstract void execute(GameEngine gameEngine);
}
