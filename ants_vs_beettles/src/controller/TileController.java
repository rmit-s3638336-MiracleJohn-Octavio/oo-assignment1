package controller;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileController extends StackPane {
	
	@FXML
	private StackPane tile;
	
	@FXML
	private Rectangle rectangle;
	
	@FXML
	private ImageView imgTile;

	public void initialize() {
		rectangle.setStroke(Color.BLACK);
		
		Image img;
		img = new Image("/assets/ant-blue.png",40,40,true,true);
//		imgTile.setFitHeight(40);
//		imgTile.setPreserveRatio(true);
    	imgTile.setImage(img);
    }
	
	@FXML
	public void rect_mouseClicked(MouseEvent event) {
		System.out.println(tile);
	}
	
	@FXML
	public void rect_mouseEntered(MouseEvent event) {
		rectangle.setFill(Color.RED);
	}
	
	@FXML
	public void rect_mouseExited(MouseEvent event) {
		rectangle.setFill(Color.WHITE);
	}

}
