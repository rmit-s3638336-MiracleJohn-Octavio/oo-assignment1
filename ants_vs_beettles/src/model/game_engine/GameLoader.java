package model.game_engine;

import controller.DashboardVC;
import controller.Helper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameLoader {
    private Stage primaryStage;

    public GameLoader(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void startGame(int boardSize, int insects) {
        FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("/view/DashboardView.fxml"));
        try {
            Parent dashboardUI = dashboardLoader.load();
            DashboardVC dashboardController = dashboardLoader.getController();

            int width = Helper.TILE_W * boardSize + (Helper.PANE_WIDTH * 2);
            int height = Helper.TILE_H * boardSize + Helper.TOP_PANE_HEIGHT * 2;
            primaryStage.setScene(new Scene(dashboardUI, width, height));

            new GameEngine(boardSize, insects, dashboardController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
