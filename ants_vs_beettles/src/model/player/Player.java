package model.player;

import com.google.java.contract.Ensures;
import model.insect.Insect;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private final int MAX_INSECT = 10;
    private Map<Integer, Insect> insects = new HashMap<>();
    private int counter = 0;

    // Disable the adding insect option
    public boolean reachedMaxInsects() {
        return insects.size() == MAX_INSECT;
    }

    @Ensures("old(insects.size() + 1) == insects.size()")
    public void placeInsect(Insect insect) {
        insects.put(counter++, insect);
    }

    @Ensures("old(insects.size() - 1) == insects.size()")
    public void removeInsect(int id) {
        // TODO
        insects.remove(id);
    }
}
