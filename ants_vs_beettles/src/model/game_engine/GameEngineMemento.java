package model.game_engine;

import model.board.Board;
import model.player.Player;

public class GameEngineMemento {
    private Board board;
    private Player[] players;

    public GameEngineMemento(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }
}
