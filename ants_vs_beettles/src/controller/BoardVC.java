package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.board.Tile;
import model.game_engine.GameEngine;
import model.insect.Insect;
import model.target.Target;

import java.io.IOException;
import java.util.List;

public class BoardVC {
    @FXML
    private Pane board;

    private TileVC[][] tileVCs;
    private GameEngine gameEngine;

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void initComponents(Tile[][] tiles, Target[] targets) {
        TileBackgroundFactory backgroundFactory = new TileBackgroundFactory();
        tileVCs = new TileVC[tiles.length][tiles.length];

        double width = 0;
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles.length; col++) {
                // Load the tile
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TileView.fxml"));
                Pane tileView;
                try {
                    tileView = loader.load();
                    TileVC tileController = loader.getController();
                    tileController.initTile(gameEngine, backgroundFactory.getBackground(row, col), row, col);
                    tileVCs[row][col] = tileController;
                    board.getChildren().add(tileView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            width += Helper.TILE_W;
        }

        // Display targets
        for (Target target : targets) {
            int targetRow = target.getTile().getX();
            int targetCol = target.getTile().getY();
            tileVCs[targetRow][targetCol].setImvTarget(target.getFullName());
        }

        board.setTranslateX(-width / 2 + 20);
    }

    public void drawBoard(Tile[][] tiles, List<Tile> validTiles, Insect currentInsect) {
        boolean highlight;
        int validTileIndex = 0;

        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles.length; col++) {
                highlight = false;
                // Highlight current insect
                if (currentInsect != null && currentInsect.getTile() != null && currentInsect.getTile().equals(tiles[row][col])) {
                    highlight = true;
                }

                // Highlight valid tiles
                if (validTileIndex < validTiles.size() && tiles[row][col].equals(validTiles.get(validTileIndex))) {
                    highlight = true;
                    validTileIndex++;
                }

                tileVCs[row][col].setHighlighter(highlight);
                tileVCs[row][col].setImvInsect(tiles[row][col].getInsect());
            }
        }
    }
}
