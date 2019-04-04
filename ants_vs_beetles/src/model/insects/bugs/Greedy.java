package model.insects.bugs;

import controller.Config.enmDirection;
import controller.Config.enmInsectType;
import javafx.scene.image.Image;
import model.insects.Insect;

public class Greedy extends Insect {

	public Greedy() {
		this.setImage(new Image("/assets/bug-blue.png",200,150,true,true));
		this.setDirection(enmDirection.right);
		setInsectType(enmInsectType.bug);
	}

}
