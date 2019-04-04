package model.tiles;

import controller.Config;
import controller.Config.enmDirection;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.insects.Insect;

public abstract class Tile extends StackPane {
	
	protected Rectangle border = new Rectangle(Config.TILE_W, Config.TILE_H);
	protected Insect insect;
	
	private Image img;
	private ImageView imv = new ImageView();
	
	public Tile() {
		
        border.setFill(null);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(1);
        
        setAlignment(Pos.CENTER);
        getChildren().addAll(border, imv);
        
        // Set Event Handler
        setOnMouseMoved(this::onMouseMoved);
        setOnMouseExited(this::onMouseExited);
        setOnMouseClicked(this::onMouseClicked);
	}
	
	// Events
	
	private void onMouseMoved(MouseEvent event) {
		border.setStroke(Color.RED);
		border.setStrokeWidth(3);
	}
	
	private void onMouseExited(MouseEvent event) {
		border.setStroke(Color.BLACK);
		border.setStrokeWidth(1);
	}
	
	private void onMouseClicked(MouseEvent event) {
		if (Config.selectedInsect == null) {
			if (insect != null) {
				Config.selectedInsect = insect;
			}
		} else {
			insect = Config.selectedInsect;
			System.out.println(insect);
			setInsect(insect);
			
			// Reset
			Config.selectedInsect = null;
		}
	}

	// Methods
	
	public void setInsect(Insect insect) {
		this.insect = insect;
		
		img = this.insect.getImage();
    	imv = new ImageView(img);
    	imv.setFitHeight(Config.TILE_W);
    	imv.setPreserveRatio(true);
    	if (this.insect.getDirection() == enmDirection.left) {
    		imv.setRotate(imv.getRotate() + 90);
    	} else {
    		imv.setRotate(imv.getRotate() + -90);
    	}
    	getChildren().add(imv);
	}
	
	// Setters and Getters	
	
	public Insect getInsect() {
		return insect;
	}
	
}
