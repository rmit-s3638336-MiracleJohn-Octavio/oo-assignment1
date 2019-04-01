package controller;

import model.Insect;

public class Config {

	// Tile Size
	public static final Double TILE_W = (double) 40;
	public static final Double TILE_H = (double) TILE_W;
	
	// Board Size
	public static final int COLUMN_COUNT = 10;
	public static final int BOARD_SIZE = COLUMN_COUNT * COLUMN_COUNT;
	
	// Window Size
	public static final Double WINDOW_W = (double) TILE_W * COLUMN_COUNT;   // tileWidth * columnCount
	public static final Double WINDOW_H = (double) WINDOW_W;
	
	// Test Switch
	public static int playerSwitch = 1;
	
	public static Insect selectedInsect;
	
	public enum enmDirection {
		left,
		right
	}
	
}
