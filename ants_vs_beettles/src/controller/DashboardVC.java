package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.insect.Insect;

import java.io.IOException;
import controller.TileVC;

public class DashboardVC extends BorderPane {

	@FXML
	private BorderPane dashboard; 
	
	@FXML
	private VBox vbxPanelLeft;
	
	@FXML
	private VBox vbxPanelRight;
	
	public void initialize() throws IOException {
		Pane board = new Pane();
		
		Boolean switchValue = false;
		int columnCount = 0;
		for (int i = 0; i < Helper.BOARD_SIZE; i++) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TileView.fxml"));
			Pane tileUI = loader.load();

			// Defines the coordinates
			tileUI.setId(Integer.toString(i));
            tileUI.setTranslateX(Helper.TILE_W * (i % Helper.COLUMN_COUNT));
            tileUI.setTranslateY(Helper.TILE_H * (i / Helper.COLUMN_COUNT));
            
            // Add image
            Image img;
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
            // Get the controller
            TileVC controller = loader.getController();
            // Assign the image
			controller.setImg(img);
			
			columnCount ++;
			if (columnCount >= Helper.COLUMN_COUNT) {
				switchValue = !switchValue;
				columnCount = 0;
			} 

			// Attach to Board
			board.getChildren().add(tileUI);
		}
		
		// Add the board to Dash-board
		dashboard.setCenter(board);
		
		// Load Panels
		loadPanels();
    }

	// Methods
	
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
	
	private String getAntImage(int imageIndex, String location) {
		String returnValue = "";
		String fileName = "";
		
		if (location == "L") {
			switch (imageIndex) {
			case 0:
				fileName = "ant-yellow";
				break;
			case 1:
				fileName = "ant-blue";
				break;
			case 2:
				fileName = "ant-red";
				break;
			default:
				break;
			}	
		} else if (location == "R") {
			switch (imageIndex) {
			case 0:
				fileName = "bug-yellow";
				break;
			case 1:
				fileName = "bug-blue";
				break;
			case 2:
				fileName = "bug-red";
				break;
			default:
				break;
			}	
		}
		
		returnValue = "/assets/" + fileName + ".png";
		return returnValue;
	}
	
	// Events
	
	@FXML
	public void topPane_clicked(MouseEvent event) {
		Helper.printMe("Clicked!");
	}

}
