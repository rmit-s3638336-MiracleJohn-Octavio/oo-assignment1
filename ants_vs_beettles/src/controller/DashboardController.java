package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

import com.sun.prism.paint.Color;

import controller.Main;
import view.Board;

public class DashboardController extends BorderPane {

	@FXML
	private BorderPane dashboard;
	
	public void initialize() throws IOException {
		Pane board = new Pane();
		board.setPrefSize(400, 400);
		
		for (int i = 0; i < 10; i++) {
			StackPane _tile = FXMLLoader.load(getClass().getResource("/view/Tile.fxml"));
			
			// Defines the coordinates
			_tile.setId(Integer.toString(i));
            _tile.setTranslateX(Config.TILE_W * (i % 10));
            _tile.setTranslateY(Config.TILE_H * (i / 10));
            
			board.getChildren().add(_tile);
		}
		dashboard.setCenter(board);
    }

	@FXML
	public void topPane_clicked(MouseEvent event) {
		System.out.println("Clicked!");
	}
	
}
