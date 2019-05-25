package controller;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game_engine.GameEngine;
import model.insect.Insect;
import model.target.Target;

public class TileVC extends Pane {
    @FXML
    private Pane tile;

    @FXML
    private Pane highlighter;

    @FXML
    private Rectangle rectangle;

    @FXML
    private ImageView imvTile;

    @FXML
    private ImageView imvInsect;

    @FXML
    private ImageView imvTarget;

    private GameEngine gameEngine;

    public void initTile(GameEngine gameEngine, int row, int col, boolean switchValue, boolean highlight, Insect insect) {
        this.gameEngine = gameEngine;

        // Background
        imvTile.setImage(getTileImage(switchValue, col));

        // Highlighter
        if (highlight) {
            highlighter.setPrefSize(Helper.TILE_W, Helper.TILE_H);
            highlighter.setStyle("-fx-background-color: rgba(100, 100, 0, 0.6);");
        }

        // Insect
        if (insect != null) {
            imvInsect.setImage(new Image("/assets/" + insect.getFullName() + ".png", Helper.TILE_W, 0, true, false));
        }

        // Get current Player
        Target target1 = gameEngine.getPlayers()[0].getTarget();
        Target target2 = gameEngine.getPlayers()[1].getTarget();
        String coordinate1 = target1.getCoordinate();
        String coordinate2 = target2.getCoordinate();

        if ( coordinate1 != null ) {
            // Ants
            coordinate1 = target1.getCoordinate();
            int target1_row =  Integer.parseInt(coordinate1.split("-")[0]);
            int target1_col =  Integer.parseInt(coordinate1.split("-")[1]);

            if ( target1_row == row && target1_row == col && !target1.getAddedToTile() ) {
                target1.setAddedToTile(true);
                imvTarget.setImage(new Image("/assets/donut.png", Helper.TILE_W, 0, true, false));
            }
        }

        if ( coordinate2 != null ) {
            // Beetles
            coordinate2 = target2.getCoordinate();
            int target2_row =  Integer.parseInt(coordinate2.split("-")[0]);
            int target2_col =  Integer.parseInt(coordinate2.split("-")[1]);

            if ( target2_row == row && target2_row == col && !target2.getAddedToTile() ) {
                target2.setAddedToTile(true);
                imvTarget.setImage(new Image("/assets/leaf.png", Helper.TILE_W, 0, true, false));
            }
        }


        // Id
        tile.setId(row + "_" + col);

        // Translate
        tile.setTranslateX(Helper.TILE_W * col);
        tile.setTranslateY(Helper.TILE_H * row);
    }

    private Image getTileImage(boolean switchValue, int i) {
        Image img = null;

        if (switchValue) {
            if (Helper.isEven(i)) {
                img = new Image("/assets/tile1.png", Helper.TILE_W, Helper.TILE_H, true, true);
            } else {
                img = new Image("/assets/tile2.png", Helper.TILE_W, Helper.TILE_H, true, true);
            }
        } else {
            if (Helper.isEven(i)) {
                img = new Image("/assets/tile2.png", Helper.TILE_W, Helper.TILE_H, true, true);
            } else {
                img = new Image("/assets/tile1.png", Helper.TILE_W, Helper.TILE_H, true, true);
            }
        }

        return img;
    }

    // GRASP - Controller + Indirection
    @FXML
    public void mouseClicked() {
        String[] coord = tile.getId().split("_");
        int x = Integer.parseInt(coord[0]);
        int y = Integer.parseInt(coord[1]);

        gameEngine.processSelectedTile(x, y);
    }

    @FXML
    public void rect_mouseEntered() {
        rectangle.setStroke(Color.RED);
        rectangle.setStrokeWidth(2);
    }

    @FXML
    public void rect_mouseExited() {
        rectangle.setStroke(Color.TRANSPARENT);
    }

    @FXML
    public void dragDetected(MouseEvent me) {
        tile.startFullDrag();
        Dragboard db = tile.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imvInsect.getImage(), 10, 10);
        ClipboardContent content = new ClipboardContent();
        content.putString("Apparently putting something in the content is all it takes for this to work .-.");
        db.setContent(content);

        mouseClicked();
        me.consume();
    }

    @FXML
    public void dragDropped(DragEvent event) {
        mouseClicked();
        event.consume();
    }

    @FXML
    public void dragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }
}

