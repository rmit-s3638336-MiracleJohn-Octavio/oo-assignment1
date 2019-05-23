package model.game_engine;

import model.game_engine.commands.UndoCommand;

import java.util.LinkedList;

public class Caretaker {
    private final int MAX_NO_OF_MEMENTO = GameEngine.NUMBER_OF_PLAYERS * UndoCommand.UNDO_LIMIT * UndoCommand.UNDOS_PER_COMMAND;
    private LinkedList<GameEngineMemento> history;

    public Caretaker() {
        history = new LinkedList<>();
    }

    public void save(GameEngine gameEngine) {
        history.add(gameEngine.save());
        if (history.size() > MAX_NO_OF_MEMENTO) {
            history.removeFirst();
        }
    }

    public void undo(GameEngine gameEngine) {
        if (history.size() > 0) {
            gameEngine.undo(history.pollLast());
            GameEngineMemento.GLOBAL_COUNTER--;
        } else {
            gameEngine.updateError("Meh");
        }
    }
}
