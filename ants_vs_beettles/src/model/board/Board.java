package model.board;

import model.insect.Insect;

import java.util.ArrayList;

public class Board {
    private static final int BOARD_SIZE = 10;

    // TODO: Extract to a new class
    private static final int PLAYER_1_VALID_PLACING_COL = 0;
    private static final int PLAYER_2_VALID_PLACING_COL = BOARD_SIZE - 1;

    private Tile[][] tiles = new Tile[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        initBoard();
        drawBoard("Init board:");
    }

    private void initBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                tiles[row][col] = new Tile(new Coordinate(row, col));
            }
        }
    }

    // TODO: VIEW
    private void drawBoard(String msg) {
        System.out.println("\n" + msg);
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print("|");
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(tiles[row][col] + "|");
            }
            System.out.println();
        }
    }

    // Show the valid tiles that the insect can be placed on / be moved to / attack
    public ArrayList<Tile> getValidTiles(int player, String choice) {
        ArrayList<Tile> validTiles;

        // TODO: implement move and attack cases
        switch (choice) {
            case "add":
                validTiles = validPlaceTiles(player);
                break;
            default:
                validTiles = null;
                break;
        }

        drawBoardWithValidTiles(validTiles, "Valid tiles to " + choice + ": ");
        return validTiles;
    }

    // TODO: VIEW
    private void drawBoardWithValidTiles(ArrayList<Tile> validTiles, String msg) {
        System.out.println("\n" + msg);
        int index = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print("|");
            for (int col = 0; col < BOARD_SIZE; col++) {
                // Default
                String content = " ";
                // Tiles that can be used
                if (index < validTiles.size() && tiles[row][col] == validTiles.get(index)) {
                    content = "*";
                    index++;
                }

                System.out.print(content + "|");
            }
            System.out.println();
        }
    }

    private ArrayList<Tile> validPlaceTiles(int player) {
        // TODO: Replace with a method?
        // Determine which player is playing
        int validCol = (player == 1) ? PLAYER_1_VALID_PLACING_COL : PLAYER_2_VALID_PLACING_COL;

        // Generate a list of valid tiles (side edge tiles with no insect in it) that the insect can be placed on
        ArrayList<Tile> validTiles = new ArrayList<>();
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (tiles[row][validCol].getInsect() == null) {
                validTiles.add(tiles[row][validCol]);
            }
        }

        return validTiles;
    }

    private ArrayList<Tile> validMoveTiles() {
        // TODO

        return null;
    }

    private ArrayList<Tile> validAttackTiles() {
        // TODO

        return null;
    }

    // Keep a record of the insect in its current tile
    public void registerInsect(Insect insect) {
        tiles[insect.getCoordinate().getX()][insect.getCoordinate().getY()].setInsect(insect);
        drawBoard("Add an insect: ");
    }
}
