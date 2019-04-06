package model.insect.ants;

import javafx.scene.image.Image;
import model.insect.Profile;

public class Scout extends Ant {
    private static Profile profile = new Profile(0, 0, 0, 0);

    public Scout() {
        super(profile);
        this.image = new Image("/assets/ant-blue.png",200,150,true,true);
        setImage(image);
    }

    @Override
    public String toString() {
        return "s";
    }
}