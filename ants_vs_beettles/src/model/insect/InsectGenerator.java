package model.insect;

import model.insect.ants.Heavy;
import model.insect.ants.Ranger;
import model.insect.ants.Scout;
import model.insect.beetles.Bogus;
import model.insect.beetles.Finder;
import model.insect.beetles.Greedy;

public class InsectGenerator {

    public Insect createInsect(String insectType) {
        Insect insect = null;
        switch (insectType) {
            case "scout":
                insect = new Scout();
                break;
            case "heavy":
                insect = new Heavy();
                break;
            case "ranger":
                insect = new Ranger();
                break;
            case "finder":
                insect = new Finder();
                break;
            case "greedy":
                insect = new Greedy();
                break;
            case "bogus":
                insect = new Bogus();
                break;
        }

        return insect;
    }
}
