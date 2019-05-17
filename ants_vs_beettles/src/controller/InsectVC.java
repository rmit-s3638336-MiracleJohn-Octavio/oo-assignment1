package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game_engine.GameEngine;
import model.insect.Insect;

import java.util.regex.Pattern;

public class InsectVC extends Pane {
    @FXML
    private Pane pneInsectPanel;

    @FXML
    private Rectangle rectangle;

    @FXML
    private ImageView imvInsect;

    // TODO: move this to BoardVC
    private GameEngine gameEngine;

    public void setImgInsect(Image img) {
        imvInsect.setImage(img);
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @FXML
    public void rect_mouseClicked() {
        if (!pneInsectPanel.getId().equals("pneInsectPanel")) {
            gameEngine.selectNewInsect(pneInsectPanel.getId());
        }
    }

    @FXML
    public void rect_mouseEntered() {
        rectangle.setStroke(Color.RED);
        rectangle.setFill(Color.AZURE);
        rectangle.setOpacity(.8);
        rectangle.setStrokeWidth(2);
    }

    @FXML
    public void rect_mouseExited() {
        rectangle.setStroke(Color.WHITE);
        rectangle.setFill(Color.LIGHTGRAY);
        rectangle.setOpacity(.8);
    }
}
