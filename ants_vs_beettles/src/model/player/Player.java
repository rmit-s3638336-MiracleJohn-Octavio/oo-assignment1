package model.player;

import com.google.java.contract.Ensures;
import model.insect.Insect;

import java.util.*;

public class Player {
    // TODO: tobe specified by the user
    private final int MAX_INSECT = 10;
    private Map<Integer, Insect> insects;
    private Set<Insect> paralysedInsects;
    private int insectId;

    public Player() {
        insects = new HashMap<>();
        paralysedInsects = new HashSet<>();
        insectId = 0;
    }

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

    @Ensures("old(paralysedInsects.size() + 1) == paralysedInsects.size()")
    public void addParalysedInsect(Insect insect) {
        paralysedInsects.add(insect);
    }

    public void deParalyseInsects() {
        for (Insect insect : paralysedInsects){
            insect.deParalyse();
            if (!insect.isParalysed()) {
                paralysedInsects.remove(insect);
            }
        }

        System.out.println("Paralysed insects: " + paralysedInsects);
    }
}
