package model.game_engine.states;

import model.board.Tile;
import model.game_engine.GameEngine;

// State pattern - Concrete State
// Singleton pattern
public class PlaceState extends State {
	private static PlaceState instance;

	private PlaceState() {

	}

	public static PlaceState getInstance() {
		if (instance == null) {
			instance = new PlaceState();
		}
		
		return instance;
	}

	@Override
	public void processSelectedTile(GameEngine gameEngine, Tile selectedTile) {
		String msg = gameEngine.placeInsectOnto(selectedTile);
		gameEngine.updateError(msg);
		gameEngine.setState(UndefinedState.getInstance());
	}
}
