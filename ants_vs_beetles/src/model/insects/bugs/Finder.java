package model.insects.bugs;

import controller.Config.enmDirection;
import controller.Config.enmInsectType;
import javafx.scene.image.Image;
import model.insects.Insect;

public class Finder extends Insect {

	public Finder() {
		this.setImage(new Image("/assets/bug-yellow.png",200,150,true,true));
		this.setDirection(enmDirection.right);
		setInsectType(enmInsectType.bug);
	}

}
