package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.board.Tile;
import model.game_engine.GameEngine;

import java.io.IOException;

public class DashboardVC extends BorderPane {

	@FXML
	private BorderPane dashboard; 
	
	@FXML
	private VBox vbxPanelLeft;
	
	@FXML
	private VBox vbxPanelRight;
	
	private GameEngine gameEngine;
	
	// Constructor
	
	public void initialize() {
		// TODO
    }

	// Methods
	
	public void drawTiles(Tile[][] tiles) {
		Pane board = new Pane();
		boolean switchValue = false;
		
		for (int row = 0; row < tiles.length; row++) {
			for (int col = 0; col < tiles.length; col++) {
				Pane tileContainer = new Pane();
				board.getChildren().add(tileContainer);

				try {
					// Add tile
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TileView.fxml"));
					Pane tileView;
					tileView = loader.load();
					// Set id and coord
					tileView.setId(row + "_" + col);
//					tileView.setTranslateX(Helper.TILE_W * col);
//					tileView.setTranslateY(Helper.TILE_H * row);
					
					// Add image
					Image img = getTileImage(switchValue, col);
		            TileVC tileController = loader.getController();
					tileController.setImg(img);
					tileController.setGameEngine(gameEngine);

					// Add to board
//					board.getChildren().add(tileView);
					tileContainer.getChildren().add(tileView);


					/// Add insect
					if (tiles[row][col].getInsect() != null) {
						FXMLLoader insectLoader = new FXMLLoader(getClass().getResource("/view/InsectView.fxml"));
						Pane insectView = insectLoader.load();
						InsectVC insectController = insectLoader.getController();
						Image insectImage = new Image("/assets/" + tiles[row][col].getInsect().getFullName() + ".png", Helper.TILE_W, Helper.TILE_H,true,false);

						insectView.setId(row + "_" + col);

						// TODO: set rotation
						insectController.setRotation(90);

						insectController.setImgInsect(insectImage);

						// Add to board
//						board.getChildren().add(insectView);
						tileContainer.getChildren().add(insectView);
					}
					
					// Set id
					tileContainer.setId(row + "_" + col);

					// Translate tileContainer
					tileContainer.setTranslateX(Helper.TILE_W * col);
					tileContainer.setTranslateY(Helper.TILE_H * row);

				} catch (IOException e) {
					// TODO: Something meaningful .-.
					System.out.println("File Not Found");
				}
			}

			switchValue = !switchValue;
		}
		
		// Add the board to Dash-board
		dashboard.setCenter(board);
		
		// Load Panels
//		loadPanels();
	}

	public void loadPanels() {
		// TODO: some creational pattern
		String[] ants = new String[]{"scout", "ranger", "heavy"};
		String[] beetles = new String[]{"finder", "bogus", "greedy"};

		for (int i = 0; i < Helper.NO_OF_INSECTS_PER_PANEL; i++) {
			// Left Panel
			loadPanel(vbxPanelLeft, ants[i], 90);

			// Right Panel
			loadPanel(vbxPanelRight, beetles[i],270);
		}
	}

	private void loadPanel(VBox vBox, String insectName, int rotation) {
		FXMLLoader loader;
		Pane insectView;
		InsectVC controller;
		Image img ;

		try {
			loader = new FXMLLoader(getClass().getResource("/view/InsectView.fxml"));
			insectView = loader.load();

			insectView.setId(insectName);

			vBox.getChildren().add(insectView);

			// Get the controller
			controller = loader.getController();
			controller.setGameEngine(gameEngine);

			// Assign the image
			img = new Image("/assets/" + insectName + ".png", 200, 200,true,true);
			controller.setImgInsect(img);
			controller.setRotation(rotation);

			// Default color is RED for visibility during edit in SceneBuilder
			// Then change to TRANSPARENT to hide
			vBox.setStyle("-fx-background-color: TRANSPARENT");
		} catch (IOException e) {
			// TODO
			System.out.println("File not found");
		}

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
	
	// Setters
	
	public void setGameEngine(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}
	
	// Events
	
	@FXML
	public void topPane_clicked(MouseEvent event) {
		Helper.printMe("Clicked!");
	}

}
