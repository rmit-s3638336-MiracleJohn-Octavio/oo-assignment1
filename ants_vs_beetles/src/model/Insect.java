package model;

import controller.Config.enmDirection;
import javafx.scene.image.Image;

public abstract class Insect {

    protected Image image;
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
   
}
