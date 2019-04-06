package model.tiles;

import controller.Config;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class AntTile extends Tile {

	private boolean isSelected = false;
	
	public AntTile() {
		// Set Event Handler
        setOnMouseMoved(this::onMouseMoved);
        setOnMouseExited(this::onMouseExited);
        setOnMouseClicked(this::onMouseClicked);
	}
	
	private void onMouseMoved(MouseEvent event) {
		border.setStroke(Color.RED);
		border.setStrokeWidth(3);
	}
	
	private void onMouseExited(MouseEvent event) {
		if (isSelected) {
			border.setStroke(Color.BLUE);
			border.setStrokeWidth(3);
		} else {
			border.setStroke(Color.BLACK);
			border.setStrokeWidth(1);
		}
		
	}
	
	private void onMouseClicked(MouseEvent event) {
//		if (Config.selectedInsect == null) {
//			if (insect != null) {
//				Config.selectedInsect = insect;
//			}
//		} else {
//			insect = Config.selectedInsect;
//			System.out.println(insect);
//			setInsect(insect);
//			
//			// Reset
//			Config.selectedInsect = null;
//		}
		if (Config.currentPlayer == Config.antPlayer) {
			isSelected = true;
			border.setStroke(Color.BLUE);
			border.setStrokeWidth(3);
		}
	}

}
