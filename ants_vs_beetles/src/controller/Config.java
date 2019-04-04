package controller;

import model.insects.Insect;
import model.players.AntPlayer;
import model.players.BugPlayer;
import model.players.Player;

public class Config {

	// Tile Size
	public static final Double TILE_W = (double) 30;
	public static final Double TILE_H = (double) TILE_W;
	
	// Board Size
	public static final int COLUMN_COUNT = 15;
	public static final int BOARD_SIZE = COLUMN_COUNT * COLUMN_COUNT;
	
	// Window Size
	public static final Double WINDOW_W = (double) TILE_W * COLUMN_COUNT;   // tileWidth * columnCount
	public static final Double WINDOW_H = (double) WINDOW_W;
	
	// Gameplay
	public static enmGameState gameState;
	public static Player currentPlayer; 
	public static Player antPlayer = new AntPlayer(); 
	public static Player bugPlayer = new BugPlayer();
	
	public static Insect selectedInsect;
	
	public enum enmDirection {
		left,
		right
	}
	
	public enum enmGameState {
		setup,
		play
	}
	
	public enum enmInsectType {
		ant,
		bug
	}
	
}
