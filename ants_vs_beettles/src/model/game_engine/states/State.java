package model.game_engine.states;

import model.board.Tile;
import model.game_engine.GameEngine;

public abstract class State {

	public void selectNewInsect(GameEngine gameEngine, String insectType) {
		gameEngine.setNewInsect(insectType);
	}

	public abstract void processSelectedTile(GameEngine gameEngine, Tile selectedTile);
}
