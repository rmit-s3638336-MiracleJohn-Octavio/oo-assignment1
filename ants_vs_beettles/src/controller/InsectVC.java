package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game_engine.GameEngine;
import model.insect.Insect;

import java.util.regex.Pattern;

public class InsectVC extends Pane {

	// Variables
	
	@FXML
	private Pane pneInsectPanel;
	
	@FXML
	private Rectangle rectangle;
	
	@FXML
	private ImageView imvInsect;

	// TODO
	private GameEngine gameEngine;
	
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

	public void setGameEngine(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
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
		String id = pneInsectPanel.getId();
		System.out.println("Insect id: " + id);

		// TODO: move the handler to tileContainer
		if (Pattern.compile("^[0-9]+[_][0-9]+$").matcher(id).matches()) {
			String[] coord = id.split("_");
			int x = Integer.parseInt(coord[0]);
			int y = Integer.parseInt(coord[1]);

			gameEngine.processSelectedTile(x, y);
		} else {
			gameEngine.selectNewInsect(pneInsectPanel.getId());
//			Helper.printMe(this.toString());
		}
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
