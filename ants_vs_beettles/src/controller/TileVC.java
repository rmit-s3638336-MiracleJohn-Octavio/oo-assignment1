package controller;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileVC extends StackPane {
	
	@FXML
	private StackPane tile;
	
	@FXML
	private Rectangle rectangle;
	
	@FXML
	private ImageView imgTile;
	
	private Image img;

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
		imgTile.setImage(img);
	}

	public void initialize() {
		rectangle.setStroke(Color.TRANSPARENT);
    }
	
	public ImageView getImgTile() {
		return imgTile;
	}

	public void setImgTile(ImageView imgTile) {
		this.imgTile = imgTile;
	}

	@FXML
	public void rect_mouseClicked(MouseEvent event) {
		System.out.println(tile);
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
