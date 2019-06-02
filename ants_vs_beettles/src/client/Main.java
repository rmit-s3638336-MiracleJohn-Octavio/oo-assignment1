package client;

import model.game_engine.GameLoader;
import controller.MainScreenVC;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
    public static void main(String[] args) {
    	launch(args);
    }
    
    @Override
	public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/view/MainScreen.fxml"));
        Parent mainScreen = mainScreenLoader.load();
        MainScreenVC mainScreenVC = mainScreenLoader.getController();

        primaryStage.setTitle("Ants Vs Beetles");

        double width = Screen.getPrimary().getBounds().getWidth();
        double height = Screen.getPrimary().getBounds().getHeight();
        primaryStage.setScene(new Scene(mainScreen, width, height));

        primaryStage.show();

        mainScreenVC.setGameLoader(new GameLoader(primaryStage));
    }
}