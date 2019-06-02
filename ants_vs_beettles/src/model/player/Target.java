package model.player;

import model.board.Tile;

import java.util.Random;

public class Target {

    private String name;
    private Tile tile;

    public Target(String name, int numOfRows, int startCol, int endCol) {
        this.name = name;
        generateCoordinate(numOfRows, startCol, endCol);
    }

    private void generateCoordinate(int numOfRow, int startCol, int endCol) {
        Random random = new Random();
        int row = random.nextInt(numOfRow);
        int col = random.nextInt(endCol - startCol) + startCol;

        tile = new Tile(row, col);
    }

    public String getFullName() {
        return name;
    }

    public Tile getTile() {
        return tile;
    }
}
