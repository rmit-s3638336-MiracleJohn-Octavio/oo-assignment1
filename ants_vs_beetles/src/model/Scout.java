package model;

import controller.Config.enmDirection;
import javafx.scene.image.Image;

public class Scout extends Insect {

	public Scout() {
		this.image = new Image("/assets/ant-yellow.png",200,150,true,true);
		this.setDirection(enmDirection.left);
	}

}
