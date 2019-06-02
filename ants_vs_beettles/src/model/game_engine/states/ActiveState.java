package model.game_engine.states;

import model.board.Tile;
import model.game_engine.GameEngine;

// State pattern - Concrete State
// Singleton pattern
public class ActiveState extends State {
	private static ActiveState instance;

	private ActiveState() {

	}

	public static ActiveState getInstance() {
		if (instance == null) {
			instance = new ActiveState();
		}

		return instance;
	}

	@Override
	public void selectNewInsect(GameEngine gameEngine, String insectType) {
		super.selectNewInsect(gameEngine, insectType);
		gameEngine.setState(PlaceState.getInstance());
	}

	@Override
	public void processSelectedTile(GameEngine gameEngine, Tile selectedTile) {
		String msg = gameEngine.actUpon(selectedTile);
		gameEngine.updateError(msg);
		gameEngine.setState(UndefinedState.getInstance());
	}
}
