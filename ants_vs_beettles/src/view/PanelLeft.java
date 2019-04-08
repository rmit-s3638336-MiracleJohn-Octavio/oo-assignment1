package view;

import controller.Config;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.board.Tile;
import model.game_engine.GameEngine;
import model.insect.ants.*;
import view.Panel;

public class PanelLeft extends Panel {

	public PanelLeft(Stage primaryStage) {
		for (int i = 0; i < 3; i++) {

        	Tile tile = new Tile(primaryStage);
        	tile.setGamePanel(this);
        	GameEngine.panelLeft = this;
        	tile.setStrokeColor(Color.BLUE);
        	tile.setPrefSize(Config.TILE_W, Config.TILE_H);
        	if (i == 0) {
        		tile.setAssignedInsect(new Heavy());
        	} else if (i == 1) { 
        		tile.setAssignedInsect(new Ranger());
        	} else if (i == 2) { 
        		tile.setAssignedInsect(new Scout());
        	}
        	
        	setMargin(tile, new Insets(2,0,2,0));
            getChildren().add(tile);
        }
	}

	 

}
