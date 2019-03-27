package model.player;

import java.util.ArrayList;
import java.util.HashMap;

import model.board.Coordinate;
import model.board.Tile;
import model.insect.Insect;

public class Player {
    private final int MAX_INSECT = 10;
    private HashMap<Integer, Insect> insects = new HashMap<>();
    private int counter = 0;

    // Disable the adding insect option
    public boolean reachedMaxInsects() {
        return insects.size() == MAX_INSECT;
    }

    public boolean placeInsect(ArrayList<Tile> validTiles, Insect insect) {
        // Player chooses an invalid tile - TODO: change to coords instead of tiles???
        if (!validTiles.contains(new Tile(insect.getCoordinate()))) {
            return false;
        }

        insects.put(counter++, insect);
        return true;
    }

    public boolean removeInsect(int id) {
        // TODO
        return false;
    }

}
