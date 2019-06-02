package model.insect.ants;

import model.board.Board;
import model.board.Tile;
import model.insect.attacks.Attack;
import model.board.valid_tiles_gen.ValidGroundMoveTilesGenerator;
import model.board.valid_tiles_gen.ValidTilesGenerator;
import model.insect.Insect;
import model.insect.Profile;

import java.util.List;

public abstract class Ant extends Insect {
    public Ant(Profile profile, ValidTilesGenerator validAttackTilesGenerator, Attack attack) {
        super(profile, new ValidGroundMoveTilesGenerator(), validAttackTilesGenerator, attack);
    }

    public Ant(Ant ant) {
        super(ant);
    }

    @Override
    public List<Tile> getValidPlaceTiles(Board board) {
        return super.getValidPlaceTilesGenerator().getValidTiles(this, board, ValidTilesGenerator.EAST, ValidTilesGenerator.SOUTH, 2);
    }
}

