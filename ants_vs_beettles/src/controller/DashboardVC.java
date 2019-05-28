package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import model.board.Tile;
import model.game_engine.GameEngine;
import model.insect.Insect;
import model.target.Target;

import java.util.List;

public class DashboardVC extends BorderPane {
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
    private Button undo;

    @FXML
    private Button heal;

    private GameEngine gameEngine;

    public void initComponents() {
        // TODO
//        dashboard.setBackground(new Background(new BackgroundImage(new Image("assets/background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        boardController.setGameEngine(gameEngine);
        leftPaneController.setGameEngine(gameEngine);
        rightPaneController.setGameEngine(gameEngine);
    }

    public void initBoard(Tile[][] tiles, Target[] targets) {
        boardController.initComponents(tiles, targets);
    }

    public void drawBoard(Tile[][] tiles, List<Tile> validTiles, Insect currentInsect) {
        boardController.drawBoard(tiles, validTiles, currentInsect);
    }

    public void loadPanels() {
        String[] ants = new String[]{"scout", "ranger", "heavy"};
        String[] beetles = new String[]{"finder", "bogus", "greedy"};

        leftPaneController.initComponents("left", ants);
        rightPaneController.initComponents("right", beetles);

        rightPaneController.setDisable(true);

        loadButtons();
    }

    private void loadButtons() {
        heal.setVisible(false);
    }

    public void setErrorMessage(String msg) {
        errorMessage.setText(msg);
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void clickedUndo() {
        gameEngine.clickedUndo();
    }

    public void clickedHeal() {
        gameEngine.heal();
    }

    public void toggleHeal(boolean displayHeal){
        heal.setVisible(displayHeal);
    }

    public void switchPlayer(int turn) {
        if (turn == 0) {
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
        boardContainer.getChildren().add(new Label(winner + " won"));
//        boardContainer.getChildren().get(0).setStyle("");
//        dashboard.setCenter(new Text(winner + " won"));
        playerTurn.setText("");
    }
}