package controller;

import model.game_engine.GameEngine;

public class Main {
    private static GameEngine gameEngine = new GameEngine();

    public static void main(String[] args) {
        mockSelectNewInsect("scout");
        mockSelectTile("5_0");
        mockSelectNewInsect("finder");
        mockSelectTile("0_9");
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

        gameEngine.processSelectedTile(x, y);
    }
}