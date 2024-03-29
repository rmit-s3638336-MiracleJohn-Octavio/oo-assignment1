package controller.side_panes;

import controller.Helper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.game_engine.GameEngine;

import java.io.IOException;

public class SidePaneVC {
    @FXML
    private VBox vBox;

    private GameEngine gameEngine;

    public void initComponents(String[] insects) {
        for (int i = 0; i < Helper.NO_OF_INSECTS_PER_PANEL; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/side_panes/InsectView.fxml"));
                Pane insectView = loader.load();

                insectView.setId(insects[i]);

                vBox.getChildren().add(insectView);

                InsectVC insectController = loader.getController();
                insectController.setGameEngine(gameEngine);

                insectController.setImgInsect(new Image("/assets/" + insects[i] + ".png", 75, 75, false, true));

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
