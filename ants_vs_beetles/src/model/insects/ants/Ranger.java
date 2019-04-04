package model.insects.ants;

import controller.Config.enmDirection;
import controller.Config.enmInsectType;
import javafx.scene.image.Image;
import model.insects.Insect;

public class Ranger extends Insect {

	public Ranger() {
		this.setImage(new Image("/assets/ant-blue.png",200,150,true,true));
		this.setDirection(enmDirection.left);
		setInsectType(enmInsectType.ant);
	}

}
