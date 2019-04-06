package model.insect.ants;

import javafx.scene.image.Image;
import model.insect.Profile;

public class Ranger extends Ant {
    private static Profile profile = new Profile(0,0,0, 0);

    public Ranger() {
        super(profile);
        this.image = new Image("/assets/ant-yellow.png",200,150,true,true);
        setImage(image);
    }

    @Override
    public String toString() {
        return "r";
    }
}
