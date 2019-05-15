package model.insect;

import model.insect.ants.Heavy;
import model.insect.ants.Ranger;
import model.insect.ants.Scout;
import model.insect.beetles.Bogus;
import model.insect.beetles.Finder;
import model.insect.beetles.Greedy;

import java.util.HashMap;
import java.util.Map;

public class InsectFactory {
    Map<String, Insect> insects = new HashMap<>();

    public InsectFactory() {
        loadInsects();
    }

    private void loadInsects(){
        insects.put("scout", new Scout());
        insects.put("heavy", new Heavy());
        insects.put("ranger", new Ranger());
        insects.put("finder", new Finder());
        insects.put("greedy", new Greedy());
        insects.put("bogus", new Bogus());
    }

    public Insect createInsect(String insectType){
        return insects.get(insectType).cloneInsect();
    }
}
