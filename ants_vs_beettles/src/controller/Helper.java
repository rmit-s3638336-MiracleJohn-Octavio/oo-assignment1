package controller;

import model.board.Board;

// GRASP - Pure Fabrication
public class Helper {
    // Tile Size
    public static final int TILE_W = 40;
    public static final int TILE_H = TILE_W;

    // Panels
    public static final int TOP_PANE_HEIGHT = 80;
    public static final int PANE_WIDTH = 147; // For Left and Right Pane
    public static final int NO_OF_INSECTS_PER_PANEL = 3;

    // Window Size
    public static final int WINDOW_W = (TILE_W * Board.BOARD_SIZE) + (PANE_WIDTH * 2);
    public static final int WINDOW_H = TILE_H * Board.BOARD_SIZE + TOP_PANE_HEIGHT * 2;

    public static void printMe(String message) {
        System.out.println(message);
    }

    public static boolean isEven(int value) {
        boolean returnValue = false;
        if (value % 2 == 0) {
            returnValue = true;
        }

        return returnValue;
    }
}
