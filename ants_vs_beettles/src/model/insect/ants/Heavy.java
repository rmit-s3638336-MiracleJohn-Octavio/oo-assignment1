package model.insect.ants;

import javafx.scene.image.Image;
import model.insect.Profile;

public class Heavy extends Ant {
    private static Profile profile = new Profile(0, 0, 0, 0);

    public Heavy() {
        super(profile);
        this.image = new Image("/assets/ant-red.png",200,150,true,true);
        setImage(image);
    }

    @Override
    public String toString() {
        return "h";
    }
}
