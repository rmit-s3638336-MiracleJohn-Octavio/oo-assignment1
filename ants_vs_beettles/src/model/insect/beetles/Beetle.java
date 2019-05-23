package model.insect.beetles;

import model.board.Board;
import model.board.Tile;
import model.insect.attacks.Attack;
import model.board.valid_tiles_gen.ValidAirMoveTilesGenerator;
import model.board.valid_tiles_gen.ValidTilesGenerator;
import model.insect.Insect;
import model.insect.Profile;

import java.util.List;

public abstract class Beetle extends Insect {
    public Beetle(Profile profile, ValidTilesGenerator validAttackTilesGenerator, Attack attack) {
        super(profile, new ValidAirMoveTilesGenerator(), validAttackTilesGenerator, attack);
    }

    public Beetle(Beetle beetle) {
        super(beetle);
    }

    @Override
    public List<Tile> getValidPlaceTiles(Board board) {
        return super.getValidPlaceTilesGenerator().getValidTiles(this, board, -1, 1, 2);
    }
}
