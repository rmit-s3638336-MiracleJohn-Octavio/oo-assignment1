package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.game_engine.GameEngine;

import java.io.IOException;

public class GameLoader {
    private Stage primaryStage;
    private MainScreenVC mainScreenVC;

    public GameLoader(Stage primaryStage, MainScreenVC mainScreenVC) {
        this.primaryStage = primaryStage;
        this.mainScreenVC = mainScreenVC;
    }

    public void startGame(int boardSize, int insects) {
        System.out.println("Board size: " + boardSize);
        System.out.println("Insects: " + insects);

        // Load dashboard
        FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("/view/DashboardView.fxml"));
        try {
            Parent dashboardUI = dashboardLoader.load();
            DashboardVC dashboardController = dashboardLoader.getController();

            primaryStage.setScene(new Scene(dashboardUI, Helper.TILE_W * boardSize + (Helper.PANE_WIDTH * 2), Helper.TILE_H * boardSize + Helper.TOP_PANE_HEIGHT * 2));

            new GameEngine(boardSize, insects, dashboardController);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
