package model.insect.beetles;

import javafx.scene.image.Image;
import model.insect.Profile;

public class Bogus extends Beetle {
    private static Profile profile = new Profile(0,0,0, 0);

    public Bogus() {
        super(profile);
        this.image = new Image("/assets/bug-blue.png",200,150,true,true);
        setImage(image);
    }

    @Override
    public void airAttack() {
        // TODO
    }

    @Override
    public String toString() {
        return "b";
    }
}