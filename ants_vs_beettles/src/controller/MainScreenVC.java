package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.game_engine.GameLoader;

public class MainScreenVC {
    private Button board;
    private GameLoader gameLoader;

    @FXML
    private HBox container;

    public void setGameLoader(GameLoader gameLoader) {
        this.gameLoader = gameLoader;
    }

    @FXML
    public void mouseClicked(MouseEvent event) {
        board = (Button) event.getSource();

        for (Node node : container.getChildren()) {
            if (node instanceof Button) {
                node.setStyle("-fx-background-color: #1e1b1c");
            }
        }
        board.setStyle("-fx-background-color: #432A31");
    }

    @FXML
    public void startGame() {
        if (board != null) {
            int boardSize = Integer.parseInt((String) board.getProperties().get("boardSize"));
            int insects = Integer.parseInt((String) board.getProperties().get("insects"));
            gameLoader.startGame(boardSize, insects);
        }
    }
}
