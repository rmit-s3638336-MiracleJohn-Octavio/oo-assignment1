package model.board;

import controller.Config;
import controller.Controls;
import controller.Config.enmDirection;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.game_engine.GameEngine;
import model.insect.*;
import model.insect.ants.Ant;
import model.insect.beetles.Beetle;
import model.player.Player1;
import model.player.Player2;
import view.Board;
import view.Panel;
import view.PanelLeft;
import view.PanelRight;

public class Tile extends StackPane {
	
	private Text text = new Text();
	private Insect assignedInsect;
	private Rectangle border = new Rectangle(Config.TILE_W, Config.TILE_H);
	private Image img;
	private ImageView imv;
	private Color strokeColor = Color.RED;
	private Object gamePanel = null; // This can be PanelLeft, PanelRight, and Board 

	public Tile(Stage primaryStage) {
		super();
		
		border.setFill(null);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(1);
        
        img = new Image("/assets/ant-red.png",200,150,true,true);
    	imv = new ImageView();
		
    	setAlignment(Pos.CENTER);
        getChildren().addAll(border, text, imv);
        
		// Events
		
		setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) {
				if (getGamePanel() instanceof PanelLeft || getGamePanel() instanceof PanelRight) {
					
					// Store "Assigned Insect" to "Selected Insect"
					if (getGamePanel() instanceof PanelLeft ) {
						if (GameEngine.currentPlayer instanceof Player1) {
							GameEngine.currentPlayer.setSelectedInsect(getAssignedInsect());
							clearBorder();
							highlightLeft();
						} else {
							// Current Player is Player2
							Controls.myAlert("Message Box", "Warning", "Player2 should make the selection from the Beetles!", AlertType.WARNING);
						}						
					} else if (getGamePanel() instanceof PanelRight ) {
						if (GameEngine.currentPlayer instanceof Player2) {
							GameEngine.currentPlayer.setSelectedInsect(getAssignedInsect());
							clearBorder();
							highlightRight();
						} else {
							// Current Player is Player1
							Controls.myAlert("Message Box", "Warning", "Player1 should make the selection from the Ants!", AlertType.WARNING);
						}
					}
				} else if (getGamePanel() instanceof Board) {
					// Check it there is currently selected Insect
					if (GameEngine.currentPlayer.getSelectedInsect() != null) {
						// Contains selection
						// So Assign it to the tile
						setAssignedInsect(GameEngine.currentPlayer.getSelectedInsect());
						
						// Change the switch
						if (GameEngine.currentPlayer instanceof Player1) {
							GameEngine.currentPlayer = GameEngine.player2;
							primaryStage.setTitle("Ants VS Beetle [Player2 Move]");
						} else if (GameEngine.currentPlayer instanceof Player2) {
							GameEngine.currentPlayer = GameEngine.player1;
							primaryStage.setTitle("Ants VS Beetle [Player1 Move]");
						}
						
						GameEngine.currentPlayer.setSelectedInsect(null);
						clearBorder();
					} else {
						// Does not contains selection
						// Need to select from the Ants
						Controls.myAlert("Message Box", "Warning", "You need to select an insect!", AlertType.WARNING);
					}
				}
			}
	
		});
        
        setOnMouseMoved(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) {
				
				if ((getGamePanel() instanceof PanelLeft || getGamePanel() instanceof PanelRight)) {
					// Panels
					
					// Reset the border on all Tiles
					
					
					// This is during insect selection	
					if (GameEngine.currentPlayer instanceof Player1) {
						if (assignedInsect instanceof Ant) {
							border.setStroke(strokeColor);
							border.setStrokeWidth(3);
						}
					} else if (GameEngine.currentPlayer instanceof Player2 ) {
						if (assignedInsect instanceof Beetle) {
							border.setStroke(strokeColor);
							border.setStrokeWidth(3);
						}
					}
					
				} else {
					// Board
					border.setStroke(strokeColor);
					border.setStrokeWidth(3);
				}
			}
		});
        
        setOnMouseExited(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) {
				
				if ((getGamePanel() instanceof PanelLeft || getGamePanel() instanceof PanelRight)) {
					// Panels
					if (GameEngine.currentPlayer.getSelectedInsect() != getAssignedInsect()) {
						border.setStroke(Color.BLACK);
						border.setStrokeWidth(1);
					}
					
				} else {
					// Board
					border.setStroke(Color.BLACK);
					border.setStrokeWidth(1);
				}
			}
		});
		
	}

	// Methods
	
	public void clearBorder() {
		Panel panelParent = null;
		
		panelParent = (Panel) GameEngine.panelLeft;
		for (Node component : panelParent.getChildren()) {
			Tile tempTile = (Tile) component;
			
			if (tempTile != this) {
				tempTile.border.setStroke(Color.BLACK);
				tempTile.border.setStrokeWidth(1);	
			}
	    }
		
		panelParent = (Panel) GameEngine.panelRight;
		for (Node component : panelParent.getChildren()) {
			Tile tempTile = (Tile) component;
			
			if (tempTile != this) {
				tempTile.border.setStroke(Color.BLACK);
				tempTile.border.setStrokeWidth(1);	
			}
	    }
	}
	
	public void highlightLeft() {
		Pane panelParent = null;
		
		panelParent = (Pane) GameEngine.panelBoard;
		for (Node component : panelParent.getChildren()) {
			Tile tempTile = (Tile) component;
			int arr[] = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90};
			for (int i=0; i<arr.length; i++) {
				System.out.println(tempTile.getText());
				System.out.println(Integer.toString(i));
				if (arr[i] == Integer.parseInt(tempTile.getText())) {
					tempTile.border.setFill(Color.LIGHTSTEELBLUE);
				}
				
			}
	    }
	}
	
	public void highlightRight() {
		Pane panelParent = null;
		
		panelParent = (Pane) GameEngine.panelBoard;
		for (Node component : panelParent.getChildren()) {
			Tile tempTile = (Tile) component;
			int arr[] = {9, 19, 29, 39, 49, 59, 69, 79, 89, 99};
			for (int i=0; i<arr.length; i++) {
				System.out.println(tempTile.getText());
				System.out.println(Integer.toString(i));
				if (arr[i] == Integer.parseInt(tempTile.getText())) {
					tempTile.border.setFill(Color.LIGHTSTEELBLUE);
				}
				
			}
	    }
	}
	
	// Getters and Setters	
	
	public void setAssignedInsect(Insect insect) {
		this.assignedInsect = insect;
		
		img = this.assignedInsect.getImage();
    	imv = new ImageView(img);
    	imv.setFitHeight(Config.TILE_W);
    	imv.setPreserveRatio(true);
    	if (insect instanceof Ant) {
    		imv.setRotate(imv.getRotate() + 90);
    	} else {
    		imv.setRotate(imv.getRotate() + -90);
    	}
    	getChildren().add(imv);
	}
	
	public Insect getAssignedInsect() {
		return assignedInsect;
	}
	
	public void setStrokeColor(Color strokeColor) {

		this.strokeColor = strokeColor;
	}

	public Object getGamePanel() {
		return gamePanel;
	}
	
	public void setGamePanel(Object gamePanel) {
		this.gamePanel = gamePanel;
	}

	
	public String getText() {
		return text.getText();
	}

	public void setText(String string) {
		this.text.setText(string);
	}
	
	
	
}
