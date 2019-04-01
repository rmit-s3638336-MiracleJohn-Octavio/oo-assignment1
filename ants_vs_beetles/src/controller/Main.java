package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.Config.enmGameState;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AntPlayer;
import model.Beast;
import model.BugPlayer;
import model.Finder;
import model.Greedy;
import model.Heavy;
import model.Insect;
import model.Player;
import model.Ranger;
import model.Scout;
import model.Tile;

public class Main extends Application {

	/*
     * +------+
     * | Init |
     * +------+    
     */
	
	private static final int PANE_WIDTH = 80;
	private static final int HEADER_HEIGHT = 80;
	
	@Override
    public void start(Stage primaryStage) throws Exception{
		
		// Set to initial state and Player
		Config.gameState = enmGameState.setup;
		Config.currentPlayer = Config.antPlayer;
		
    	BorderPane objBorderPane = new BorderPane();
        objBorderPane.setTop(loadHeader());
        
        Insect arrAnts[] = {new Heavy(), new Ranger(), new Scout()};
        Insect arrBugs[] = {new Beast(), new Greedy(), new Finder()};
        
        objBorderPane.setLeft(loadSelection(arrAnts));
        objBorderPane.setCenter(loadBoard());
        objBorderPane.setRight(loadSelection(arrBugs));

        // Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
        primaryStage.setTitle("Ants VS Bugs");
        primaryStage.setScene(new Scene(objBorderPane, Config.WINDOW_W + (PANE_WIDTH *2), Config.WINDOW_H + HEADER_HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    /*
     * +---------------+
     * | Other Methods |
     * +---------------+    
     */
    
    private HBox loadHeader() {
		HBox objHBox = new HBox();
		objHBox.setPrefHeight(HEADER_HEIGHT);
		objHBox.setStyle("-fx-background-color: derive(" + "#3b5998" + ", 0%)");			
		
		try {
			// Add Children			
	        Image img = new Image("/assets/antsvsbugs-logo.png",200,150,true,true);
	        ImageView imv = new ImageView(img);
	        objHBox.setPadding(new Insets(10, 5, 10, 15));
	        objHBox.setAlignment(Pos.BOTTOM_LEFT);
	        objHBox.getChildren().add(imv);	
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		// Return 
		return objHBox;
	}
    
    private Pane loadBoard() {
        Pane pane = new Pane();

        // Create an array of Tiles 
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < Config.BOARD_SIZE; i++) {
            tiles.add(new Tile(String.valueOf("")));
        }

        // Shuffle it
        Collections.shuffle(tiles);
        
        for (int i = 0; i < tiles.size(); i++) {
        	
        	// Returns the element at a specified position in this list
            Tile tile = tiles.get(i);
            
            // Defines the coordinates
            tile.setTranslateX(Config.TILE_W * (i % Config.COLUMN_COUNT));
            tile.setTranslateY(Config.TILE_H * (i / Config.COLUMN_COUNT));
            
            // Appends the specified element to the end of the list
            pane.getChildren().add(tile);
        }

         System.out.println((Double.toString(pane.getWidth())));
//         root.setStyle("-fx-background-color: red");
        
        return pane;
    }
    
    private VBox loadSelection(Insect arrInsects[]) {
    	VBox vbox = new VBox();
    	vbox.setPrefWidth(PANE_WIDTH);
    	
        for (int i = 0; i < arrInsects.length; i++) {
        	Tile tile = new Tile("");
        	tile.setPrefSize(Config.TILE_W, Config.TILE_H);
        	tile.setInsect(arrInsects[i]);
        	
        	VBox.setMargin(tile, new Insets(2,0,2,0));
            vbox.getChildren().add(tile);
        }
        return vbox;
    }

}
