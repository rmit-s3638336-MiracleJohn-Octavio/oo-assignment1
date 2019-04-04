package model.insects;

import controller.Config.enmDirection;
import controller.Config.enmInsectType;
import javafx.scene.image.Image;

public abstract class Insect {

    private Image image;
    private enmInsectType insectType;
    private enmDirection direction;
    
    public Insect() {
    }
   
    public enmDirection getDirection() {
		return direction;
	}

	public void setDirection(enmDirection direction) {
		this.direction = direction;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public enmInsectType getInsectType() {
		return insectType;
	}

	public void setInsectType(enmInsectType insectType) {
		this.insectType = insectType;
	}
   
}
