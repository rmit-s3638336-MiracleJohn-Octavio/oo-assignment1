package controller.board;

import controller.Helper;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.board.Tile;
import model.game_engine.GameEngine;
import model.insect.Insect;
import model.player.Target;

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
        initTiles(tiles);
        initTargets(targets);

        // Center the board
        double boardWidth = tiles.length * Helper.TILE_W;
        double offset = Helper.TILE_W;
        board.setTranslateX((-boardWidth + offset) / 2);
        board.setTranslateY((-boardWidth + offset) / 2);
    }

    private void initTiles(Tile[][] tiles) {
        TileViewFactory tileFactory = new TileViewFactory();
        tileVCs = new TileVC[tiles.length][tiles.length];

        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles.length; col++) {
                tileVCs[row][col] = tileFactory.getTileVC(gameEngine, row, col);
                board.getChildren().add(tileVCs[row][col].getTile());
            }
        }
    }

    private void initTargets(Target[] targets) {
        for (Target target : targets) {
            int targetRow = target.getTile().getX();
            int targetCol = target.getTile().getY();
            tileVCs[targetRow][targetCol].setImvTarget(target.getFullName());
        }
    }

    public void drawBoard(Tile[][] tiles, List<Tile> validTiles, Insect currentInsect) {
        boolean highlight;
        int validTileIndex = 0;

        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles.length; col++) {
                highlight = false;

                // Highlight valid tiles
                if (validTileIndex < validTiles.size() && tiles[row][col].equals(validTiles.get(validTileIndex))) {
                    highlight = true;
                    validTileIndex++;
                }

                tileVCs[row][col].setHighlighter(highlight);
                tileVCs[row][col].setImvInsect(tiles[row][col].getInsect());
            }
        }

        // Highlight current insect
        if (currentInsect != null && currentInsect.getTile() != null) {
            int currentInsectX = currentInsect.getTile().getX();
            int currentInsectY = currentInsect.getTile().getY();
            tileVCs[currentInsectX][currentInsectY].setHighlighter(true);
        }
    }
}
