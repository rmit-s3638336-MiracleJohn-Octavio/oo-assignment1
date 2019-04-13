package controller;

import com.google.java.contract.Requires;
import model.game_engine.GameEngine;

import java.util.regex.Pattern;

public class Main {
    private static GameEngine gameEngine = new GameEngine();

    public static void main(String[] args) {
        mockSelectNewInsect("scout");
        mockSelectNewInsect("heavy");
        mockSelectTile("5_0");

        mockSelectNewInsect("finder");
        mockSelectTile("0_9");

        mockSelectTile("0_11");
    }

    // TODO: some event handlers

    // Mock handler
    public static void mockSelectNewInsect(String insectType) {
        gameEngine.selectNewInsect(insectType);
    }

    // Mock handler
    @Requires("Pattern.compile(\"^[0-9]+[_][0-9]+$\").matcher(tileCoord).matches()")
    public static void mockSelectTile(String tileCoord) {
        String[] coord = tileCoord.split("_");
        int x = Integer.parseInt(coord[0]);
        int y = Integer.parseInt(coord[1]);

        gameEngine.processSelectedTile(x, y);
    }
}