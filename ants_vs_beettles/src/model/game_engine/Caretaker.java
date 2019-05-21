package model.game_engine;

import java.util.LinkedList;

public class Caretaker {
    private LinkedList<GameEngineMemento> history;

    public Caretaker() {
        history = new LinkedList<>();
    }

    public void save(GameEngine gameEngine) {
        history.add(gameEngine.save());
//        System.out.println("Added: " + history.getLast());
        if (history.size() > 12) {
            history.removeFirst();
        }
    }

    public void undo(GameEngine gameEngine) {
        if (history.size() > 0) {
            gameEngine.undo(history.pollLast());
        } else {
            gameEngine.updateError("Meh");
        }
    }
}
