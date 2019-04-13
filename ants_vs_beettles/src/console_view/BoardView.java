package console_view;

import model.board.Tile;

import java.util.ArrayList;

public class BoardView {
    public void drawBoard(Tile[][] tiles) {
        System.out.println("\nBoard: ");

        printIndices(tiles.length);

        for (int row = 0; row < tiles.length; row++) {
            System.out.print(row + "\t|");
            for (int col = 0; col < tiles[row].length; col++) {
                System.out.print(tiles[row][col] + "\t|");
            }
            System.out.println();
        }
    }

    public void drawBoardWithValidTiles(Tile[][] tiles, ArrayList<Tile> validTiles) {
        System.out.println("\nValid tiles (*): ");

        printIndices(tiles.length);

        int index = 0;
        for (int row = 0; row < tiles.length; row++) {
            System.out.print(row + "\t|");
            for (int col = 0; col < tiles[row].length; col++) {
                // Default
                String content = tiles[row][col].toString();
                // Tiles that can be used
                if (index < validTiles.size() && tiles[row][col] == validTiles.get(index)) {
                    content = "*";
                    index++;
                }

                System.out.print(content + "\t|");
            }
            System.out.println();
        }
    }

    private void printIndices(int max) {
        System.out.print("\t|");
        for (int i = 0; i < max; i++) {
            System.out.print(i + "\t|");
        }
        System.out.println();
    }
}
