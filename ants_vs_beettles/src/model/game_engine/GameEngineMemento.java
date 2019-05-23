package model.game_engine;

import model.board.Board;

public class GameEngineMemento {
    public static int GLOBAL_COUNTER = 0;
    private int counter;
    private Board board;

    public GameEngineMemento(Board board) {
        this.board = new Board(board);
        counter = GLOBAL_COUNTER++;
        System.out.println("Saved board number " + counter);
    }

    public Board getBoard() {
        return board;
    }

    public int getCounter() {
        return counter;
    }
}
