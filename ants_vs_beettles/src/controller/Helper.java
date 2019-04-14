package controller;

public class Helper {

	// Tile Size
	public static final Double TILE_W = (double) 40;
	public static final Double TILE_H = (double) TILE_W;
	
	// Panels
	public static final int PANE_WIDTH = 147; // For Left and Right Pane
	public static final int HEADER_HEIGHT = 100;
	public static final int FOOTER_HEIGHT = 20;
	
	// Board Size
	public static final int COLUMN_COUNT = 12;
	public static final int BOARD_SIZE = COLUMN_COUNT * COLUMN_COUNT;
	
	// Window Size
	public static final Double WINDOW_W = (double) (TILE_W * COLUMN_COUNT) + (PANE_WIDTH * 2);   
	public static final Double WINDOW_H = (double) (TILE_H * COLUMN_COUNT);
	
	public static void printMe(String message) {
		System.out.println(message);
	}
	
	public static Boolean isEven(int value) {
		Boolean returnValue = false;
		if (value % 2 == 0) {
			returnValue = true;
		}
	         
		return returnValue;
		
	}
	
}
