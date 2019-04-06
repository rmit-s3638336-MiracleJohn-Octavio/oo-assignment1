package view;

import controller.Config;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import model.board.Tile;
import model.insect.beetles.*;

public class PanelRight extends Panel {

	public PanelRight() {
		for (int i = 0; i < 3; i++) {

        	Tile tile = new Tile();
        	tile.setStrokeColor(Color.BLUE);
        	tile.setPrefSize(Config.TILE_W, Config.TILE_H);
        	if (i == 0) {
        		tile.setInsect(new Greedy());
        	} else if (i == 1) { 
        		tile.setInsect(new Bogus());
        	} else if (i == 2) { 
        		tile.setInsect(new Finder());
        	}
        	
        	setMargin(tile, new Insets(2,0,2,0));
            getChildren().add(tile);
        }
	}

}
