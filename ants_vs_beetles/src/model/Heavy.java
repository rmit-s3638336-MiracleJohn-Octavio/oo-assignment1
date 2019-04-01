package model;

import controller.Config.enmDirection;
import javafx.scene.image.Image;

public class Heavy extends Insect {

	public Heavy() {
		this.image = new Image("/assets/ant-red.png",200,150,true,true);
		this.setDirection(enmDirection.left);
	}
}
