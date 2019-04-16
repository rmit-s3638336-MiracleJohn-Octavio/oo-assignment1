package controller;

import com.google.java.contract.Requires;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.game_engine.GameEngine;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class Main extends Application {
	
    private static GameEngine gameEngine;

    public static void main(String[] args) {
    	launch(args);
    }
    
    @Override
	public void start(Stage primaryStage) throws Exception {
    	// Load dashboard
        FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("/view/DashboardView.fxml"));
        Parent dashboardUI = dashboardLoader.load();
        DashboardVC dashboardController = dashboardLoader.getController();

        // Setup stage
        primaryStage.setTitle("Ants VS Beetle");
        primaryStage.setScene(new Scene(dashboardUI, Helper.WINDOW_W, Helper.WINDOW_H));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            primaryStage.setTitle(newVal.toString());
        });
        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            primaryStage.setTitle(newVal.toString());
        });

        // Start the engine
    	gameEngine = new GameEngine(dashboardController);
    }
}