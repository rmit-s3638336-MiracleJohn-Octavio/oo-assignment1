package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Screen;
import model.board.Board;
import model.game_engine.GameEngine;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
    private static GameEngine gameEngine;

    public static void main(String[] args) {
    	launch(args);
    }
    
    @Override
	public void start(Stage primaryStage) throws Exception {
    	// Load dashboard
//        FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("/view/DashboardView.fxml"));
//        Parent dashboardUI = dashboardLoader.load();
//        DashboardVC dashboardController = dashboardLoader.getController();

        // Setup stage
//        primaryStage.setTitle("Ants Vs Beetles");
//        Scene gameScene = new Scene(dashboardUI, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
//        primaryStage.setScene(new Scene(dashboardUI, Helper.TILE_W * Board.OLD_BOARD_SIZE + (Helper.PANE_WIDTH * 2), Helper.TILE_H * Board.OLD_BOARD_SIZE + Helper.TOP_PANE_HEIGHT * 2));
//        primaryStage.setScene(gameScene);
//        primaryStage.setResizable(false);
//        primaryStage.show();

        // Start the engine
//    	gameEngine = new GameEngine(dashboardController);



        FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/view/MainScreen.fxml"));
        Parent mainScreen = mainScreenLoader.load();
        MainScreenVC mainScreenVC = mainScreenLoader.getController();


        // Setup stage
        primaryStage.setTitle("Ants Vs Beetles");
        Scene mainScene = new Scene(mainScreen, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        primaryStage.setScene(mainScene);
        primaryStage.show();

        mainScreenVC.setGameLoader(new GameLoader(primaryStage, mainScreenVC));
    }
}