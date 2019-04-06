package model.insect.beetles;

import javafx.scene.image.Image;
import model.insect.Profile;

public class Greedy extends Beetle {
    private static Profile profile = new Profile(0,0,0, 0);

    public Greedy() {
        super(profile);
        this.image = new Image("/assets/bug-red.png",200,150,true,true);
        setImage(image);
    }

    @Override
    public void airAttack() {
        // TODO
    }

    @Override
    public String toString() {
        return "g";
    }
}
