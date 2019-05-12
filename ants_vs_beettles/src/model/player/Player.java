package model.player;

import com.google.java.contract.Ensures;
import model.insect.Insect;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private final int MAX_INSECT = 10;
    private Map<Integer, Insect> insects = new HashMap<>();
    private int insectId = 0;

    public boolean reachedMaxInsects() {
        return insects.size() == MAX_INSECT;
    }

    @Ensures("old(insects.size() + 1) == insects.size()")
    public int placeInsect(Insect insect) {
        int id = insectId;
        insects.put(insectId++, insect);
        return id;
    }

    public boolean containsInsect(Insect insect) {
        return insects.containsValue(insect);
    }

    @Ensures("old(insects.size() - 1) == insects.size()")
    public void removeInsect(int id) {
        insects.remove(id);
    }
}
