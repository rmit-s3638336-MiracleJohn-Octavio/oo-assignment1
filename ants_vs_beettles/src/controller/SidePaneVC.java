package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.game_engine.GameEngine;

import java.io.IOException;

public class SidePaneVC {
    @FXML
    ImageView background;

    @FXML
    private VBox vBox;

    private GameEngine gameEngine;

    public void initComponents(String id, String[] insects) {
        background.setImage(new Image("/assets/" + id + ".jpg"));

        for (int i = 0; i < Helper.NO_OF_INSECTS_PER_PANEL; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InsectView.fxml"));
                Pane insectView = loader.load();

                insectView.setId(insects[i]);

                vBox.getChildren().add(insectView);

                InsectVC insectController = loader.getController();
                insectController.setGameEngine(gameEngine);

                insectController.setImgInsect(new Image("/assets/" + insects[i] + ".png", 65, 65, false, true));

                // Default color is RED for visibility during edit in SceneBuilder
                // Then change to TRANSPARENT to hide
                vBox.setStyle("-fx-background-color: TRANSPARENT");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void setDisable(boolean disable) {
        vBox.setDisable(disable);
    }
}
