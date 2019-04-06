package model.insect.beetles;

import javafx.scene.image.Image;
import model.insect.Profile;

public class Finder extends Beetle {
    private static Profile profile = new Profile(0,0,0, 0);

    public Finder() {
        super(profile);
        this.image = new Image("/assets/bug-yellow.png",200,150,true,true);
        setImage(image);
    }

    @Override
    public void airAttack() {
        // TODO
    }

    @Override
    public String toString() {
        return "f";
    }
}
