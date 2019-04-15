package controller;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game_engine.GameEngine;

public class TileVC extends Pane {
	
	@FXML
	private Pane tile;
	
	@FXML
	private Rectangle rectangle;
	
	@FXML
	private ImageView imvTile;
	
	@FXML
	private ImageView imvInsect;
	
	private Image img;
	
	GameEngine gameEngine;

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
		imvTile.setImage(img);
	}

	public void initialize() {
		rectangle.setStroke(Color.TRANSPARENT);
    }
	
	public ImageView getImgTile() {
		return imvTile;
	}

	public void setImgTile(ImageView imgTile) {
		this.imvTile = imgTile;
	}

	// Setters
	
	public void setGameEngine(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}
	
	// Events
		
	@FXML
	public void rect_mouseClicked(MouseEvent event) {
		gameEngine.selectNewInsect(tile.getId());
	}
	
	@FXML
	public void rect_mouseEntered(MouseEvent event) {
		rectangle.setStroke(Color.RED);
		rectangle.setStrokeWidth(2);
	}
	
	@FXML
	public void rect_mouseExited(MouseEvent event) {
		rectangle.setStroke(Color.TRANSPARENT);
	}

}
