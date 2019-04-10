package controller;

import console_view.Menu;
import model.game_engine.GameEngine;

public class Main {
    private static GameEngine gameEngine = new GameEngine();
    private static Menu menu = new Menu();

    public static void main(String[] args) {
        int choice;
        while (true) {
            choice = menu.getMenuOption(gameEngine.getTurn());

            switch (choice) {
                case 1:
                    String insectType = processInsectOption(gameEngine.getTurn(), menu.getInsectOption(gameEngine.getTurn()));
                    mockSelectNewInsect(insectType);
                    break;
                case 2:
                    String tileCoord = menu.getCoord();
                    mockSelectTile(tileCoord);
                    break;
            }
        }
    }

    // TODO: some event handlers

    private static String processInsectOption(int turn, int option) {
        if (turn == 0) {
            switch (option) {
                case 1:
                    return "scout";
                case 2:
                    return "heavy";
                case 3:
                    return "ranger";
            }
        } else {
            switch (option) {
                case 1:
                    return "finder";
                case 2:
                    return "greedy";
                case 3:
                    return "bogus";
            }
        }

        return "";
    }

    // Mock handler
    private static void mockSelectNewInsect(String insectType) {
        gameEngine.selectNewInsect(insectType);
    }

    // Mock handler
    private static void mockSelectTile(String tileCoord) {
        String[] coord = tileCoord.split("_");
        int x = Integer.parseInt(coord[0]);
        int y = Integer.parseInt(coord[1]);

        gameEngine.processSelectedTile(x, y);
    }
}