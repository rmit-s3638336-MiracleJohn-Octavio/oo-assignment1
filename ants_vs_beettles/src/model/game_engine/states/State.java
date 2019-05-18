package model.game_engine.states;

import model.board.Tile;
import model.game_engine.GameEngine;

public abstract class State {

	public void selectNewInsect(GameEngine gameEngine, String insectType) {
//	    if (gameEngine.getCurrentPlayer().reachedMaxInsects()) {
//	    	gameEngine.updateError("Cannot add more insects.");
//            return;
//        }
//
//	    gameEngine.setCurrentInsectToNewInsect(insectType);
//        
//	    gameEngine.setCurrentValidTiles();
//
//	    gameEngine.updateViews();    
		gameEngine.setNewInsect(insectType);
	}

	public abstract void processSelectedTile(GameEngine gameEngine, Tile selectedTile);
}
