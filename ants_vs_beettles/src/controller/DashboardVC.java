package controller;

import controller.board.BoardVC;
import controller.side_panes.SidePaneVC;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import model.board.Tile;
import model.game_engine.GameEngine;
import model.insect.Insect;
import model.player.Target;

import java.util.List;

public class DashboardVC extends BorderPane {
    @FXML
    private VBox info;

    @FXML
    private Label playerTurn;

    @FXML
    private Label errorMessage;

    @FXML
    private BorderPane dashboard;

    @FXML
    private SidePaneVC leftPaneController;

    @FXML
    private SidePaneVC rightPaneController;

    @FXML
    private BoardVC boardController;

    @FXML
    private HBox boardContainer;

    @FXML
    private Button heal;

    private GameEngine gameEngine;

    public void initComponents(Tile[][] tiles, Target[] targets) {
        boardController.setGameEngine(gameEngine);
        leftPaneController.setGameEngine(gameEngine);
        rightPaneController.setGameEngine(gameEngine);

        loadPanels();
        loadButtons();
        initBoard(tiles, targets);
    }

    private void loadPanels() {
        String[] ants = new String[]{"scout", "ranger", "heavy"};
        String[] beetles = new String[]{"finder", "bogus", "greedy"};

        leftPaneController.initComponents(ants);
        rightPaneController.initComponents(beetles);

        rightPaneController.setDisable(true);
    }

    private void loadButtons() {
        heal.setVisible(false);
    }

    private void initBoard(Tile[][] tiles, Target[] targets) {
        info.setPrefWidth(dashboard.getWidth() - heal.getWidth() - Helper.TILE_W);
        boardController.initComponents(tiles, targets);
    }

    public void drawBoard(Tile[][] tiles, List<Tile> validTiles, Insect currentInsect) {
        boardController.drawBoard(tiles, validTiles, currentInsect);
    }

    public void setErrorMessage(String msg) {
        errorMessage.setText(msg);
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void toggleHeal(boolean displayHeal){
        heal.setVisible(displayHeal);
    }

    public void switchPlayer(int turn) {
        if (turn == GameEngine.ANTS_TURN) {
            leftPaneController.setDisable(false);
            rightPaneController.setDisable(true);
            playerTurn.setText("Current player: Team Ants");
        } else {
            leftPaneController.setDisable(true);
            rightPaneController.setDisable(false);
            playerTurn.setText("Current player: Team Beetles");
        }
    }

    public void declareWinner(String winner) {
        boardContainer.getChildren().remove(0);
        boardContainer.getChildren().add(new Label(winner + " won :)"));
        boardContainer.getChildren().get(0).setStyle("-fx-text-fill: #F7CE86");
        playerTurn.setText("");
    }

    @FXML
    public void clickedUndo() {
        gameEngine.clickedUndo();
    }

    @FXML
    public void clickedHeal() {
        gameEngine.heal();
    }
}