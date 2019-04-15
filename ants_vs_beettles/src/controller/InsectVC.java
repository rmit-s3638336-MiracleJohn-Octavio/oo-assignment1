package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.insect.Insect;

public class InsectVC extends Pane {

	// Variables
	
	@FXML
	private Pane pneInsectPanel;
	
	@FXML
	private Rectangle rectangle;
	
	@FXML
	private ImageView imvInsect;
	
	private Image imgInsect;
	private int rotation;
	
	// Constructors
	
	public InsectVC() {
	}

	public InsectVC(Node... children) {
		super(children);
	}

	// Setters and Getters
	
	public Image getImgInsect() {
		return imgInsect;
	}

	public void setImgInsect(Image img) {
		this.imgInsect = img;
		imvInsect.setImage(img);		
	}
	
	// Events
	
	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
		imvInsect.setRotate(rotation);
	}

	@FXML
	public void rect_mouseClicked(MouseEvent event) {
		
		Helper.printMe(this.toString());
	}
	
	@FXML
	public void rect_mouseEntered(MouseEvent event) {
		rectangle.setStroke(Color.RED);
		rectangle.setFill(Color.AZURE);
		rectangle.setOpacity(.8);
		rectangle.setStrokeWidth(2);
	}
	
	@FXML
	public void rect_mouseExited(MouseEvent event) {
		rectangle.setStroke(Color.WHITE);
		rectangle.setFill(Color.LIGHTGRAY);
		rectangle.setOpacity(.8);
	}

}
