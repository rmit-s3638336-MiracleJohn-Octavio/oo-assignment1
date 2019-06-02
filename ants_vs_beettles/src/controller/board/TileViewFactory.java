package controller.board;

import controller.Helper;
import model.game_engine.GameEngine;

import java.util.HashMap;
import java.util.Map;

// Flyweight pattern
public class TileViewFactory {
    private Map<String, TileView> tiles = new HashMap<>();

    public TileViewFactory() {
        loadTiles();
    }

    private void loadTiles() {
        tiles = new HashMap<>();
        tiles.put("odd", new TileView("/assets/tile1.png"));
        tiles.put("even", new TileView("/assets/tile2.png"));
    }

    public TileVC getTileVC(GameEngine gameEngine, int row, int col) {
        String tileType = (Helper.isEven(row + col)) ? "even" : "odd";
        return tiles.get(tileType).createTile(gameEngine, row, col);
    }
}
