package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.game_engine.GameEngine;
import view.Board;
import view.Header;
import view.PanelLeft;
import view.PanelRight;

public class Main extends Application {

    private static GameEngine gameEngine = new GameEngine();
    public static Stage mainStage;

    /*
     * +------+
     * | Start |
     * +------+    
     */
    
    public static void main(String[] args) {
    	launch(args);
    }
    
    @Override
	public void start(Stage primaryStage) throws Exception {
    	mainStage = primaryStage;
    	
    	Parent root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
    	
    	primaryStage.setTitle("Ants VS Beetle [Player1 Move]");
        primaryStage.setScene(new Scene(root, Config.WINDOW_W + (Config.PANE_WIDTH *2), Config.WINDOW_H));
        primaryStage.show();
    	
        // This is to determine the current width and height of the whole window
        
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
        	primaryStage.setTitle(newVal.toString());
       });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
        	primaryStage.setTitle(newVal.toString());
       });
	}
	
}