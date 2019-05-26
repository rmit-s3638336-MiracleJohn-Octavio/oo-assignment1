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

//    @FXML
//    private VBox vbxPanelLeft;
//
//    @FXML
//    private VBox vbxPanelRight;

    @FXML
    private Button undo;

    @FXML
    private Button heal;

    private SidePaneVC leftPaneVC;
    private SidePaneVC rightPaneVC;


    private BoardVC boardVC;

    private GameEngine gameEngine;

    public void initComponents() {
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/view/BoardView.fxml"));
            dashboard.setCenter(loader.load());
            boardVC = loader.getController();
            boardVC.setGameEngine(gameEngine);

            loader = new FXMLLoader(getClass().getResource("/view/SidePaneView.fxml"));
            dashboard.setLeft(loader.load());
            leftPaneVC = loader.getController();
            leftPaneVC.setGameEngine(gameEngine);

            loader = new FXMLLoader(getClass().getResource("/view/SidePaneView.fxml"));
            dashboard.setRight(loader.load());
            rightPaneVC = loader.getController();
            rightPaneVC.setGameEngine(gameEngine);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initBoard(Tile[][] tiles) {
        boardVC.initComponents(tiles);
    }

    public void drawBoard(Tile[][] tiles, List<Tile> validTiles, Insect currentInsect) {
        boardVC.drawBoard(tiles, validTiles, currentInsect);
    }

    public void loadPanels() {
        String[] ants = new String[]{"scout", "ranger", "heavy"};
        String[] beetles = new String[]{"finder", "bogus", "greedy"};

        leftPaneVC.initComponents("left", ants);
        rightPaneVC.initComponents("right", beetles);

        rightPaneVC.setDisable(true);

        loadButtons();
    }

    private void loadButtons() {
        undo.setTranslateX(Helper.WINDOW_W / 2 - undo.getWidth() / 2);
        heal.setTranslateX(150);
        heal.setVisible(false);
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

    public void clickedHeal() {
        System.out.println("DashboardVC.clickedHeal()");
        gameEngine.heal();
    }

    public void toggleHeal(boolean displayHeal){
        heal.setVisible(displayHeal);
    }

    public void switchPlayer(int turn) {
        if (turn == 0) {
            leftPaneVC.setDisable(false);
            rightPaneVC.setDisable(true);
            playerTurn.setText("Current player: Team Ants");
        } else {
            leftPaneVC.setDisable(true);
            rightPaneVC.setDisable(false);
            playerTurn.setText("Current player: Team Beetles");
        }
    }
}