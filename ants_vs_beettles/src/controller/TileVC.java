package controller;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game_engine.GameEngine;
import model.insect.Insect;

public class TileVC extends Pane {
	@FXML
	private Pane tile;

	@FXML
	private Pane highlighter;
	
	@FXML
	private Rectangle rectangle;
	
	@FXML
	private ImageView imvTile;
	
	@FXML
	private ImageView imvInsect;
	
	private GameEngine gameEngine;

	public void initTile(GameEngine gameEngine, int row, int col, boolean switchValue, boolean highlight, Insect insect) {
		this.gameEngine = gameEngine;

		// Background
		imvTile.setImage(getTileImage(switchValue, col));

		// Highlighter
		if (highlight) {
			highlighter.setPrefSize(Helper.TILE_W, Helper.TILE_H);
			highlighter.setStyle("-fx-background-color: rgba(100, 100, 0, 0.6);");
		}

		// Insect
		if (insect != null) {
			imvInsect.setImage(new Image("/assets/" + insect.getFullName() + ".png", Helper.TILE_W, 0, true, false));
		}

		// Id
		tile.setId(row + "_" + col);

		// Translate
		tile.setTranslateX(Helper.TILE_W * col);
		tile.setTranslateY(Helper.TILE_H * row);
	}

	private Image getTileImage(boolean switchValue, int i) {
		Image img = null;

		if (switchValue) {
			if (Helper.isEven(i)) {
				img = new Image("/assets/tile1.png", Helper.TILE_W, Helper.TILE_H,true,true);
			} else {
				img = new Image("/assets/tile2.png", Helper.TILE_W, Helper.TILE_H,true,true);
			}
		} else {
			if (Helper.isEven(i)) {
				img = new Image("/assets/tile2.png", Helper.TILE_W, Helper.TILE_H,true,true);
			} else {
				img = new Image("/assets/tile1.png", Helper.TILE_W, Helper.TILE_H,true,true);
			}
		}

		return img;
	}

	@FXML
	public void mouseClicked() {
		String[] coord = tile.getId().split("_");
		int x = Integer.parseInt(coord[0]);
		int y = Integer.parseInt(coord[1]);

		gameEngine.processSelectedTile(x, y);
	}
	
	@FXML
	public void rect_mouseEntered() {
		rectangle.setStroke(Color.RED);
		rectangle.setStrokeWidth(2);
	}
	
	@FXML
	public void rect_mouseExited() {
		rectangle.setStroke(Color.TRANSPARENT);
	}
}
