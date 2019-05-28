package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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
