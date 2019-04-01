package model;

import controller.Config.enmDirection;
import javafx.scene.image.Image;

public class Beast extends Insect {

	public Beast() {
		this.image = new Image("/assets/bug-red.png",200,150,true,true);
		this.setDirection(enmDirection.right);
	}

}
