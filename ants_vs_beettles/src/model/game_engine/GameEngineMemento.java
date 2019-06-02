package model.game_engine;

import model.board.Board;

// Memento pattern - Memento
public class GameEngineMemento {
    private Board board;

    public GameEngineMemento(Board board) {
        this.board = new Board(board);
    }

    public Board getBoard() {
        return board;
    }
}
