package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import controller.TileVC;

public class DashboardVC extends BorderPane {

	@FXML
	private BorderPane dashboard;
	
	public void initialize() throws IOException {
		Pane board = new Pane();
		
		Boolean switchValue = false;
		int columnCount = 0;
		for (int i = 0; i < Helper.BOARD_SIZE; i++) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TileUI.fxml"));
			StackPane tileUI = loader.load();

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
            TileVC controller = loader.getController();
			controller.setImg(img);
			
			columnCount ++;
			if (columnCount >= Helper.COLUMN_COUNT) {
				switchValue = !switchValue;
				columnCount = 0;
			} 

			// Attach to Board
			board.getChildren().add(tileUI);
		}
		
		Image img = new Image("/assets/left.jpg",100,500,true,true);
		ImageView imgv = new ImageView();
		imgv.setImage(img);;
		
		Pane panel1 = new Pane();
		panel1.setStyle("-fx-background-color: red;");
//		panel1.getChildren().add(imgv);

		Pane panel2 = new Pane();
		panel2.setStyle("-fx-background-color: red;");
		
		dashboard.setCenter(board);
    }

	@FXML
	public void topPane_clicked(MouseEvent event) {
		Helper.printMe("Clicked!");
	}

}
