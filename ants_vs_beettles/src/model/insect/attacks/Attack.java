package model.insect.attacks;

import model.board.Board;
import model.insect.Insect;
import model.player.Player;

public interface Attack {

    public abstract void attack(Insect attacker, Board board, Player player, Insect attackee);

}
