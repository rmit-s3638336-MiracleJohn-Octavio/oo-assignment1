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
import controller.TileVC;

public class DashboardVC extends BorderPane {

	@FXML
	private BorderPane dashboard; 
	
	@FXML
	private VBox vbxPanelLeft;
	
	@FXML
	private VBox vbxPanelRight;
	
	GameEngine gameEngine;
	
	// Constructor
	
	public void initialize() throws IOException {
//		drawTiles();
    }

	// Methods
	
	public void drawTiles(Tile[][] tiles) {
		Pane board = new Pane();
		Boolean switchValue = false;
		
		for (int row = 0; row < tiles.length; row++) {
			for (int col = 0; col < tiles.length; col++) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TileView.fxml"));
				Pane tileView;
				try {
					tileView = loader.load();
					// Set id and coord
					tileView.setId(row + "_" + col);
					tileView.setTranslateX(Helper.TILE_W * col);
					tileView.setTranslateY(Helper.TILE_H * row);
					
					// Add image
					Image img = getTileImage(switchValue, col);
		            TileVC tileController = loader.getController();
					tileController.setImg(img);
					tileController.setGameEngine(gameEngine);

					// Validate
					if (tiles[row][col].getInsect() != null) {
						// Add insect
						FXMLLoader insectLoader = new FXMLLoader(getClass().getResource("/view/InsectView.fxml"));
						Pane insectView = insectLoader.load();
						InsectVC insectController = insectLoader.getController();
						Image insectImage = new Image("/assets/" + tiles[row][col].getInsect().getFullName() + ".png",40,40,true,true);
						insectController.setImgInsect(insectImage);
					}
					
					// Add to board
					board.getChildren().add(tileView);
				} catch (IOException e) {
					System.out.println("File Not Found");
				}
			}
			switchValue = !switchValue;
		}
		
		// Add the board to Dash-board
		dashboard.setCenter(board);
		
		// Load Panels
		loadPanels();
	}
	
	private void loadPanels() throws IOException {
		FXMLLoader loader;
		Pane insectView;
		InsectVC controller;
		Image img ;
		for (int i = 0; i < 3; i++) {
			
			// Left Panel
			
			loader = new FXMLLoader(getClass().getResource("/view/InsectView.fxml"));
			insectView = loader.load();
			vbxPanelLeft.getChildren().add(insectView);
			// Get the controller
            controller = loader.getController();
            // Assign the image
            img = new Image(getAntImage(i, "L"), 200, 200,true,true);
			controller.setImgInsect(img);
			controller.setRotation(90);
			
			// Right Panel
			
			loader = new FXMLLoader(getClass().getResource("/view/InsectView.fxml"));
			insectView = loader.load();
			vbxPanelRight.getChildren().add(insectView);
			// Get the controller
            controller = loader.getController();
            // Assign the image
            img = new Image(getAntImage(i, "R"), 200, 200,true,true);
			controller.setImgInsect(img);
			controller.setRotation(270);
		}		
		// Default color is RED for visibility during edit in SceneBuilder
		// Then change to TRANSPARENT to hide
		vbxPanelLeft.setStyle("-fx-background-color: TRANSPARENT");
		vbxPanelRight.setStyle("-fx-background-color: TRANSPARENT");
	}
	
	private Image getTileImage(Boolean switchValue, int i) {
		Image img = null;
		
		if (switchValue) {
        	if (Helper.isEven(i)) {
        		img = new Image("/assets/tile1.png",40,40,true,true);	
        	} else {
        		img = new Image("/assets/tile2.png",40,40,true,true);
        	}
        } else {
        	if (Helper.isEven(i)) {
        		img = new Image("/assets/tile2.png",40,40,true,true);	
        	} else {
        		img = new Image("/assets/tile1.png",40,40,true,true);
        	}
        }
		
		return img;
	}
	
	private String getAntImage(int imageIndex, String location) {
		String returnValue = "";
		String fileName = "";
		
		if (location == "L") {
			switch (imageIndex) {
			case 0:
				fileName = "scout";
				break;
			case 1:
				fileName = "ranger";
				break;
			case 2:
				fileName = "heavy";
				break;
			default:
				break;
			}	
		} else if (location == "R") {
			switch (imageIndex) {
			case 0:
				fileName = "finder";
				break;
			case 1:
				fileName = "bogus";
				break;
			case 2:
				fileName = "greedy";
				break;
			default:
				break;
			}	
		}
		
		returnValue = "/assets/" + fileName + ".png";
		return returnValue;
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
