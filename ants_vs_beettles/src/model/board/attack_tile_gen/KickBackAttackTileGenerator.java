package model.board.attack_tiles_gen;

import model.board.Tile;
import model.insect.Insect;

public class KickBackAttackTileGenerator implements AttackTileGenerator {
    // TODO: check if the newCoord is valid; if not, create a HPAttackTile
    @Override
    public Tile getAttackTile(Insect insect, Tile tile) {
        int damage = insect.getProfile().getDamage();
        Tile attackerLoc = insect.getTile();

        // Calc new pos
        int dX = tile.getX() - attackerLoc.getX();
        int dY = tile.getY() - attackerLoc.getY();


        return null;
    }
}
