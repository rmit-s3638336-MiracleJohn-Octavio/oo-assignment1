package model.game_engine.states;

import model.board.Tile;
import model.game_engine.GameEngine;

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
//	@Override
//	public void processSelectedTile(GameEngine gameEngine,Tile selectedTile) {
//		 if (gameEngine.validTileSelection(selectedTile)) {
//	            int id = gameEngine.getCurrentPlayer().placeInsect(gameEngine.getCurrentInsect());
//	            gameEngine.getCurrentInsect().initInsect(id, selectedTile);
//	            selectedTile.setInsect(gameEngine.getCurrentInsect());
//	            gameEngine.toggleTurn();
//
//	            gameEngine.updateError("");
//	        }
//
//		 gameEngine.reset();
//	        
//	        gameEngine.updateError("The insect cannot be placed on the selected tile.");
//	        gameEngine.setState(UndefinedState.getInstance());
//	}

	@Override
	public void processSelectedTile(GameEngine gameEngine, Tile selectedTile) {
		String msg = gameEngine.placeInsectOnto(selectedTile);
		gameEngine.updateError(msg);
		gameEngine.setState(UndefinedState.getInstance());
	}

}
