package model;

import controller.Config.enmDirection;
import javafx.scene.image.Image;

public class Ranger extends Insect {

	public Ranger() {
		this.image = new Image("/assets/ant-blue.png",200,150,true,true);
		this.setDirection(enmDirection.left);
	}

}
