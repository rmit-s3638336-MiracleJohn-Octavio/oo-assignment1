package controller;

import model.board.Coordinate;
import model.board.Tile;
import model.game_engine.GameEngine;

public class Main {
    private static GameEngine gameEngine = new GameEngine();

    public static void main(String[] args) {
        // TODO: Generate the food (which class???) - TO BE IMPLEMENTED IN THE SECOND ASSIGNMENT?

        // GAME LOOP
        // TODO: change condition to checkWin()
        boolean keepOnLooping = true;
        while (keepOnLooping) {
            // TODO: enable and disable insects; I HAVE NO IDEA HOW TO DO THIS!!!!
            mockSelectNewInsect("scout");
            mockSelectTile("5_0");
            mockSelectNewInsect("finder");
            mockSelectTile("0_9");
            keepOnLooping = false;
        }
    }

    // TODO: some event handlers

    // Mock handler
    public static void mockSelectNewInsect(String insectType) {
        gameEngine.selectNewInsect(insectType);
    }

    // Mock handler
    public static void mockSelectTile(String tileCoord) {
        String[] coord = tileCoord.split("_");
        int x = Integer.parseInt(coord[0]);
        int y = Integer.parseInt(coord[1]);

        Tile selectedTile = new Tile(new Coordinate(x, y));
        gameEngine.processSelectedTile(selectedTile);
    }
}