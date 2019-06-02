package model.game_engine.states;

import model.board.Tile;
import model.game_engine.GameEngine;

// State pattern - Concrete State
// Singleton pattern
public class UndefinedState extends State {
	private static UndefinedState instance;

	private UndefinedState() {

	}

	public static UndefinedState getInstance() {
		if (instance == null) {
			instance = new UndefinedState();
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
		if (gameEngine.setCurrentInsect(selectedTile)) {
			gameEngine.toggleHeal(true);
			gameEngine.setState(ActiveState.getInstance());
		}
	}
}