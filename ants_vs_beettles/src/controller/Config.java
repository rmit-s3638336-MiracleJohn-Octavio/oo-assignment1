package controller;

//import model.Insect;

public class Config {

	// Tile Size
	public static final Double TILE_W = (double) 40;
	public static final Double TILE_H = (double) TILE_W;
	
	public static final int PANE_WIDTH = 80; // For Left and Right Pane
	public static final int HEADER_HEIGHT = 80;
	
	// Board Size
	public static final int COLUMN_COUNT = 10;
	public static final int BOARD_SIZE = COLUMN_COUNT * COLUMN_COUNT;
	
	// Window Size
	public static final Double WINDOW_W = (double) TILE_W * COLUMN_COUNT;   // tileWidth * columnCount
	public static final Double WINDOW_H = (double) WINDOW_W + HEADER_HEIGHT;
	
//	public static Insect selectedInsect;
	
	public enum enmDirection {
		left,
		right
	}
	
	public enum enmPlayerSwitch {
		player1,
		player2
	}
	
}
