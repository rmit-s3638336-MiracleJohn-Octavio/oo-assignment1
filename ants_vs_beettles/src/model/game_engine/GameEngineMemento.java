package model.game_engine;

import model.board.Board;

public class GameEngineMemento {
    private Board board;

    public GameEngineMemento(Board board) {
        this.board = new Board(board);
    }

    public Board getBoard() {
        return board;
    }
}
