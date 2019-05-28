package controller;

// GRASP - Pure Fabrication
public class Helper {
    // Tile Size
    public static final int TILE_W = 40;
    public static final int TILE_H = TILE_W;

    // Panels
    public static final int TOP_PANE_HEIGHT = 120;
    public static final int PANE_WIDTH = 147; // For Left and Right Pane
    public static final int NO_OF_INSECTS_PER_PANEL = 3;

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
