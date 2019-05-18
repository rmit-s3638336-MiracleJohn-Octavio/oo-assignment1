package model.player;

import com.google.java.contract.Ensures;
import model.insect.Insect;
import model.target.Roots;
import model.target.Sugar;
import model.target.Target;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private final int MAX_INSECT = 10;
    private Map<Integer, Insect> insects = new HashMap<>();
    private Target target;
    private int insectId = 0;
    private String insectType;

    public Player(String insectType) {
        this.insectType = insectType;

        switch (this.insectType) {
            case "ant":
                target = new Sugar();
                break;
            case "beetle":
                target = new Roots();
        }
    }

    public boolean reachedMaxInsects() {
        return insects.size() == MAX_INSECT;
    }

    @Ensures("old(insects.size() + 1) == insects.size()")
    public void placeInsect(Insect insect) {
        insects.put(insectId++, insect);
    }

    public boolean containsInsect(Insect insect) {
        return insects.containsValue(insect);
    }

    @Ensures("old(insects.size() - 1) == insects.size()")
    public void removeInsect(int id) {
        insects.remove(id);
    }

    public Target getTarget() {
        return target;
    }
}
