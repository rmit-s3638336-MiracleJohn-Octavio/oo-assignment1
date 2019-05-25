package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.board.Tile;
import model.game_engine.GameEngine;
import model.insect.Insect;

import java.io.IOException;
import java.util.List;

public class DashboardVC extends BorderPane {
    @FXML
    private Label playerTurn;

    @FXML
    private Label errorMessage;

    @FXML
    private BorderPane dashboard;

    @FXML
    private VBox vbxPanelLeft;

    @FXML
    private VBox vbxPanelRight;

    @FXML
    private Button undo;

    private GameEngine gameEngine;

    public void drawBoard(Tile[][] tiles, List<Tile> validTiles, Insect currentInsect) {
        Pane board = new Pane();
        boolean switchValue = false;
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

                // Load the tile
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TileView.fxml"));
                Pane tileView;
                try {
                    tileView = loader.load();
                    TileVC tileController = loader.getController();
                    tileController.initTile(gameEngine, row, col, switchValue, highlight, tiles[row][col].getInsect());
                    board.getChildren().add(tileView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            switchValue = !switchValue;
        }

        dashboard.setCenter(board);
    }

    public void loadPanels() {
        String[] ants = new String[]{"scout", "ranger", "heavy"};
        String[] beetles = new String[]{"finder", "bogus", "greedy"};

        for (int i = 0; i < Helper.NO_OF_INSECTS_PER_PANEL; i++) {
            // Left Panel
            loadPanel(vbxPanelLeft, ants[i]);

            // Right Panel
            loadPanel(vbxPanelRight, beetles[i]);
        }

        vbxPanelRight.setDisable(true);

        loadButtons();
    }

    // DRY
    private void loadPanel(VBox vBox, String insectName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InsectView.fxml"));
            Pane insectView = loader.load();

            insectView.setId(insectName);

            vBox.getChildren().add(insectView);

            InsectVC insectController = loader.getController();
            insectController.setGameEngine(gameEngine);

            insectController.setImgInsect(new Image("/assets/" + insectName + ".png", 65, 65, false, true));

            // Default color is RED for visibility during edit in SceneBuilder
            // Then change to TRANSPARENT to hide
            vBox.setStyle("-fx-background-color: TRANSPARENT");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadButtons() {
        undo.setTranslateX(Helper.WINDOW_W / 2 - undo.getWidth() / 2);
    }

    public void setErrorMessage(String msg) {
        errorMessage.setText(msg);
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void setMode(MouseEvent event) {
        String mode = ((Button) event.getSource()).getId();

        gameEngine.setMode(mode);
    }

    public void clickedUndo() {
        gameEngine.clickedUndo();
    }

    public void switchPlayer(int turn) {
        if (turn == 0) {
            vbxPanelLeft.setDisable(false);
            vbxPanelRight.setDisable(true);
            playerTurn.setText("Current player: Team Ants");
        } else {
            vbxPanelLeft.setDisable(true);
            vbxPanelRight.setDisable(false);
            playerTurn.setText("Current player: Team Beetles");
        }
    }

}