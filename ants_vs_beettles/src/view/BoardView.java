package view;

import model.board.TileOld;

import java.util.ArrayList;

public class BoardView {
    public void drawBoard(TileOld[][] tiles) {
        System.out.println("\nBoard: ");
        for (int row = 0; row < tiles.length; row++) {
            System.out.print("|");
            for (int col = 0; col < tiles[row].length; col++) {
                System.out.print(tiles[row][col] + "|");
            }
            System.out.println();
        }
    }

    public void drawBoardWithValidTiles(TileOld[][] tiles, ArrayList<TileOld> validTiles) {
        System.out.println("\nValid tiles (*): ");
        int index = 0;
        for (int row = 0; row < tiles.length; row++) {
            System.out.print("|");
            for (int col = 0; col < tiles[row].length; col++) {
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
}