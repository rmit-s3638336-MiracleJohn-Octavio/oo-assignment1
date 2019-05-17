package model.insect.attacks;

import model.board.Board;
import model.insect.Insect;
import model.player.Player;

public class PoisonAttack extends HPAttack {
    @Override
    public void attack(Insect attacker, Board board, Player player, Insect attackee) {
        super.attack(attacker, board, player, attackee);
        if (!attackee.killed()) {
            attackee.setParalysis(2);
            player.addParalysedInsect(attackee);
        }
    }
}
