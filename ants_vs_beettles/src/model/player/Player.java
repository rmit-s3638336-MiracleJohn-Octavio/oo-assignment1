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
    private int undoLimit = 3;

    public Player() {
        insects = new HashMap<>();
        System.out.println("Insects: " + insects);
        paralysedInsects = new HashSet<>();
        insectId = 0;
    }

    public Player(Player player) {
        insects = new HashMap<>();
//        insects = player.cloneInsects();
        paralysedInsects = new HashSet<>(player.paralysedInsects);
        insectId = player.insectId;
        undoLimit = player.undoLimit - 1;
    }

//    private HashMap<Integer, Insect> cloneInsects() {
//        HashMap<Integer, Insect> clones = new HashMap<>();
//
//        Iterator iterator = insects.keySet().iterator();
//        while (iterator.hasNext()) {
//            int id = (int) iterator.next();
//            Insect currInsect = insects.get(id);
//            Insect insect = currInsect.cloneInsect();
//            insect.setTile(currInsect.getTile());
//            clones.put(id, insect);
//        }
//
//        return clones;
//    }

    public boolean reachedMaxInsects() {
        return insects.size() == MAX_INSECT;
    }

    public boolean reachedUndoLimit() {
        return undoLimit == 0;
    }

    public Map<Integer, Insect> getInsects() {
        return insects;
    }

    public void addInsectWithId(int id, Insect insect) {
        insects.put(id, insect);
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
