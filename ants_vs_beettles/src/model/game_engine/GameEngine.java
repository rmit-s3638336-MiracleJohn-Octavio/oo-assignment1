package model.game_engine;

import controller.Config.enmPlayerSwitch;
import model.player.Player;
import model.player.Player1;
import model.player.Player2;

public class GameEngine {

	// Create new players	
	public static Player player1 = new Player1();
	public static Player player2 = new Player2();
	
	// Store the current player here
	public static Player currentPlayer = player1;
	
	public static Object panelBoard = null;
	public static Object panelLeft = null;
	public static Object panelRight = null;
}
