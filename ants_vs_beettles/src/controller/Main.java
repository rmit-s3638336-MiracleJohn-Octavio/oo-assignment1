package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.board.Tile;
import model.game_engine.GameEngine;
import view.Board;
import view.Header;
import view.Panel;
import view.PanelLeft;
import view.PanelRight;

public class Main extends Application {

//    private static GameEngine gameEngine = new GameEngine();

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
    	BorderPane objBorderPane = new BorderPane();
    	objBorderPane.setTop(new Header());
    	objBorderPane.setLeft(new PanelLeft());
    	objBorderPane.setCenter(new Board());
    	objBorderPane.setRight(new PanelRight());
    	
    	primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(objBorderPane, Config.WINDOW_W + (Config.PANE_WIDTH *2), Config.WINDOW_H));
        primaryStage.show();
    	
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
        	primaryStage.setTitle(newVal.toString());
       });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
        	primaryStage.setTitle(newVal.toString());
       });
        
//    	mockSelectNewInsect("scout");
//        mockSelectTile("5_0");
//        mockSelectNewInsect("finder");
//        mockSelectTile("0_9");
	}

    // TODO: some event handlers

    // Mock handler
    public static void mockSelectNewInsect(String insectType) {
//        gameEngine.selectNewInsect(insectType);
    }

    // Mock handler
    public static void mockSelectTile(String tileCoord) {
//        String[] coord = tileCoord.split("_");
//        int x = Integer.parseInt(coord[0]);
//        int y = Integer.parseInt(coord[1]);
//
//        gameEngine.processSelectedTile(x, y);
    }

	
}