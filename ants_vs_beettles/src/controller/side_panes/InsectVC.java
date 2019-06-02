package controller.side_panes;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.game_engine.GameEngine;

public class InsectVC extends Pane {
    @FXML
    private Pane pneInsectPanel;

    @FXML
    private Rectangle rectangle;

    @FXML
    private ImageView imvInsect;

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
        rectangle.setOpacity(1.0);
    }

    @FXML
    public void rect_mouseExited() {
        rectangle.setOpacity(0.7);
    }

    @FXML
    public void dragDetected(MouseEvent event) {
        imvInsect.startFullDrag();
        Dragboard db = imvInsect.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imvInsect.getImage(), 20, 20);
        ClipboardContent content = new ClipboardContent();
        content.putString("Meh");
        db.setContent(content);

        rect_mouseClicked();
        event.consume();
    }
}
