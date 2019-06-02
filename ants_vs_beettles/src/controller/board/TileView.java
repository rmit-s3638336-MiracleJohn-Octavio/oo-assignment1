package controller.board;

import controller.Helper;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import model.game_engine.GameEngine;

import java.io.IOException;

public class TileView {
    private Image background;

    public TileView(String imageUrl) {
        background = new Image(imageUrl, Helper.TILE_W, Helper.TILE_H, true, true);
    }

    public TileVC createTile(GameEngine gameEngine, int row, int col) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/board/TileView.fxml"));

        TileVC tileController = null;
        try {
            loader.load();
            tileController = loader.getController();
            tileController.initTile(gameEngine, background, row, col);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tileController;
    }
}
