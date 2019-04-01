package model;

import controller.Config.enmDirection;
import javafx.scene.image.Image;

public class Greedy extends Insect {

	public Greedy() {
		this.image = new Image("/assets/bug-blue.png",200,150,true,true);
		this.setDirection(enmDirection.right);
	}

}
