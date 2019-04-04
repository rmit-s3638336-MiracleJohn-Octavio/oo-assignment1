package model.insects.bugs;

import controller.Config.enmDirection;
import controller.Config.enmInsectType;
import javafx.scene.image.Image;
import model.insects.Insect;

public class Beast extends Insect {

	public Beast() {
		this.setImage(new Image("/assets/bug-red.png",200,150,true,true));
		setInsectType(enmInsectType.bug);
		setDirection(enmDirection.right);
	}

}
