package model.insects.ants;

import java.io.IOException;

import controller.Config.enmDirection;
import controller.Config.enmInsectType;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import model.insects.Insect;

public class Heavy extends Insect {

	public Heavy() throws IOException {
		this.setImage(new Image("/assets/ant-red.png",200,150,true,true));
		this.setDirection(enmDirection.left);
		setInsectType(enmInsectType.ant);
	}
}
