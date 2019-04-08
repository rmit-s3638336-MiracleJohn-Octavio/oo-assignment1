package model.player;

import model.insect.Insect;

import java.util.HashMap;
import java.util.Map;

public class PlayerOld {
    private final int MAX_INSECT = 10;
    private Map<Integer, Insect> insects = new HashMap<>();
    private int counter = 0;

    // Disable the adding insect option
    public boolean reachedMaxInsects() {
        return insects.size() == MAX_INSECT;
    }

    public void placeInsect(Insect insect) {
        insects.put(counter++, insect);
    }

    public void removeInsect(int id) {
        // TODO
        insects.remove(id);
    }
}
