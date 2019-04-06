package model.insect;

import javafx.scene.image.Image;

public abstract class Insect {
	
	protected Image image;
	private int healthPoints;
    private Profile profile;

    public Insect(Profile profile) {
        this.profile = profile;
        healthPoints = profile.getMaxHealthPoints();
    }

    public void decreaseHealthPoints(int damage) {
        healthPoints -= damage;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
