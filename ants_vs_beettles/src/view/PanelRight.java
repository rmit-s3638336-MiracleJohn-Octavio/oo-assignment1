package view;

import controller.Config;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.board.Tile;
import model.game_engine.GameEngine;
import model.insect.beetles.*;

public class PanelRight extends Panel {

	public PanelRight(Stage primaryStage) {
		for (int i = 0; i < 3; i++) {

        	Tile tile = new Tile();
        	tile.setGamePanel(this);
        	GameEngine.panelRight = this;
        	tile.setStrokeColor(Color.BLUE);
        	tile.setPrefSize(Config.TILE_W, Config.TILE_H);
        	if (i == 0) {
        		tile.setAssignedInsect(new Greedy());
        	} else if (i == 1) { 
        		tile.setAssignedInsect(new Bogus());
        	} else if (i == 2) { 
        		tile.setAssignedInsect(new Finder());
        	}
        	
        	setMargin(tile, new Insets(2,0,2,0));
            getChildren().add(tile);
        }
	}

}
