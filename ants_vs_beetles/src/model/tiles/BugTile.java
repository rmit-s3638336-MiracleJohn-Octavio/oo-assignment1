package model.tiles;

import controller.Config;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BugTile extends Tile {

	public BugTile() {
		
		// Set Event Handler
        setOnMouseMoved(this::onMouseMoved);
        setOnMouseExited(this::onMouseExited);
        setOnMouseClicked(this::onMouseClicked);
	}

	private void onMouseMoved(MouseEvent event) {
		border.setStroke(Color.BLUE);
		border.setStrokeWidth(3);
	}
	
	private void onMouseExited(MouseEvent event) {
		border.setStroke(Color.BLACK);
		border.setStrokeWidth(1);
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
		
		System.out.println(this);
	}
	
}
