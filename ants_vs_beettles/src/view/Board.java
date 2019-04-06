package view;

import java.util.ArrayList;
import java.util.List;
import controller.Config;
import javafx.scene.layout.Pane;
import model.board.Tile;

public class Board extends Pane {

	public Board() {
		
		// Create an array of Tiles 
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < Config.BOARD_SIZE; i++) {
            tiles.add(new Tile());
        }
        
		for (int i = 0; i < tiles.size(); i++) {
        	
        	// Returns the element at a specified position in this list
            Tile tile = tiles.get(i);
            
            // Defines the coordinates
            tile.setTranslateX(Config.TILE_W * (i % Config.COLUMN_COUNT));
            tile.setTranslateY(Config.TILE_H * (i / Config.COLUMN_COUNT));
            
            // Appends the specified element to the end of the list
            getChildren().add(tile);
        }
	}

}
