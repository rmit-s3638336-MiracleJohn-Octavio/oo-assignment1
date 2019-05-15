package model.player;

import com.google.java.contract.Ensures;
import model.insect.Insect;

import java.util.*;

public class Player {
    private final int MAX_INSECT = 10;
    private Map<Integer, Insect> insects;
    private List<Insect> paralysedInsects;
    private int insectId;

    public Player() {
        insects = new HashMap<>();
        paralysedInsects = new ArrayList<>();
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

    public void addParalysedInsect(Insect insect) {
        // TODO; duplicates???
        paralysedInsects.add(insect);
    }

    public void deParalyseInsects() {
        for (int i = 0; i < paralysedInsects.size(); i++) {
            Insect currInsect = paralysedInsects.get(i);
            currInsect.deParalyse();
            // TODO: another way of removing insect?
            if (!currInsect.isParalysed()) {
                paralysedInsects.remove(i);
                i--;
            }
        }

        System.out.println("Paralysed insects: " + paralysedInsects);
    }
}
