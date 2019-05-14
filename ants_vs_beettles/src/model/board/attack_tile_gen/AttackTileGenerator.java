package model.board.attack_tiles_gen;

import model.board.Tile;
import model.insect.Insect;

public interface AttackTileGenerator {
    public Tile getAttackTile(Insect insect, Tile tile);
}
