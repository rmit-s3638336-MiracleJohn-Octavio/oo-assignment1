package controller;

import com.google.java.contract.Requires;
import model.game_engine.GameEngine;

import java.util.regex.Pattern;

public class Main {
    private static GameEngine gameEngine = new GameEngine();

    public static void main(String[] args) {
        System.out.println("\n\nSelect a Scout: ");
        mockSelectNewInsect("scout");

        System.out.println("\n\nSelect a Heavy: ");
        mockSelectNewInsect("heavy");

        System.out.println("\n\nPut the insect onto the board: ");
        mockSelectTile("5_0");

        System.out.println("\n\nNew Finder: ");
        mockSelectNewInsect("finder");

        System.out.println("\n\nPut the insect onto the board: ");
        mockSelectTile("0_9");

        System.out.println("\n\nSelect an invalid tile: ");
        mockSelectTile("0_11");

        System.out.println("\n\nSelect an existing insect: ");
        mockSelectTile("5_0");

        System.out.println("\n\nSelect MOVE: ");
        mockSelectOption("move");

        System.out.println("\n\nMove to a new position: ");
        mockSelectTile("4_0");
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

    // Mock handler
    public static void mockSelectOption(String option) {
        gameEngine.setMode(option);
    }
}