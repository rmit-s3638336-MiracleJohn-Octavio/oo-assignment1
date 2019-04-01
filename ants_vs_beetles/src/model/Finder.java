package model;

import controller.Config.enmDirection;
import javafx.scene.image.Image;

public class Finder extends Insect {

	public Finder() {
		this.image = new Image("/assets/bug-yellow.png",200,150,true,true);
		this.setDirection(enmDirection.right);
	}

}
