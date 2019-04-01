package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Beast;
import model.Finder;
import model.Greedy;
import model.Heavy;
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
    	BorderPane objBorderPane = new BorderPane();
        objBorderPane.setTop(loadHeader());
        
        objBorderPane.setCenter(createBoard());
        objBorderPane.setLeft(getLeftSection());
        objBorderPane.setRight(getRightSection());

        //        Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(objBorderPane, Config.WINDOW_W + (PANE_WIDTH *2), Config.WINDOW_H + HEADER_HEIGHT));
        primaryStage.show();
        
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
        	primaryStage.setTitle(newVal.toString());
       });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
        	primaryStage.setTitle(newVal.toString());
       });
        
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
    
    private static Pane createBoard() {
        Pane pane = new Pane();

//        root.setPrefSize(Config.WINDOW_W, Config.WINDOW_H);

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
    
    private VBox getLeftSection() {
    	VBox vbox = new VBox();
    	vbox.setPrefWidth(PANE_WIDTH);
        
        for (int i = 0; i < 3; i++) {

        	Tile tile = new Tile("");
        	tile.setPrefSize(Config.TILE_W, Config.TILE_H);
        	if (i == 0) {
        		tile.setInsect(new Heavy());
        	} else if (i == 1) { 
        		tile.setInsect(new Ranger());
        	} else if (i == 2) { 
        		tile.setInsect(new Scout());
        	}
        	
        	VBox.setMargin(tile, new Insets(2,0,2,0));
            vbox.getChildren().add(tile);
            
        }
        
        return vbox;
    }
    
    private VBox getRightSection() {
    	VBox vbox = new VBox();
    	vbox.setPrefWidth(PANE_WIDTH);
        
    	for (int i = 0; i < 3; i++) {

        	Tile tile = new Tile("");
        	tile.setPrefSize(Config.TILE_W, Config.TILE_H);
        	if (i == 0) {
        		tile.setInsect(new Beast());
        	} else if (i == 1) { 
        		tile.setInsect(new Greedy());
        	} else if (i == 2) { 
        		tile.setInsect(new Finder());
        	}
        	
        	VBox.setMargin(tile, new Insets(2,0,2,0));
            vbox.getChildren().add(tile);
            
        }
        
        return vbox;
    }

    class MyLabel extends Label {
        
        public MyLabel(String text) {
            super(text);
            
            setAlignment(Pos.BASELINE_CENTER);
        }
    }
}
