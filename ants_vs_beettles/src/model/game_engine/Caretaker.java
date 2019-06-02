package model.game_engine;

import java.util.LinkedList;

// Memento pattern - Caretaker
// Command pattern - Receiver
public class Caretaker {
    private final int MAX_NO_OF_MEMENTOS = 14;
    private LinkedList<GameEngineMemento> history;

    public Caretaker() {
        history = new LinkedList<>();
    }

    public void save(GameEngine gameEngine) {
        history.add(gameEngine.save());
        if (history.size() > MAX_NO_OF_MEMENTOS) {
            history.removeFirst();
        }
    }

    public void undo(GameEngine gameEngine) {
        if (history.size() > 0) {
            gameEngine.undo(history.pollLast());
        } else {
            gameEngine.updateError("Out of boards to load.");
        }
    }
}
