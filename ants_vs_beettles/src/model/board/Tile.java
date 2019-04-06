package model.board;

import controller.Config;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.insect.*;

public class Tile extends StackPane {
	
	private Insect insect;
	private Rectangle border = new Rectangle(Config.TILE_W, Config.TILE_H);
	private Image img;
	private ImageView imv;
	private Color strokeColor = Color.RED;

	public Tile() {
		super();
		
		border.setFill(null);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(1);
        
        img = new Image("/assets/ant-red.png",200,150,true,true);
    	imv = new ImageView();
		
    	setAlignment(Pos.CENTER);
        getChildren().addAll(border, imv);
        
		// Events
		
		setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) {
//				if (Config.selectedInsect == null) {
//					if (insect != null) {
//						Config.selectedInsect = insect;
//					}
//				} else {
//					insect = Config.selectedInsect;
//					System.out.println(insect);
//					setInsect(insect);
//					
//					// Reset
//					Config.selectedInsect = null;
//				}
			}
	
		});
        
        setOnMouseMoved(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) {
				border.setStroke(strokeColor);
				border.setStrokeWidth(3);
			}
	
		});
        
        setOnMouseExited(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) {
				border.setStroke(Color.BLACK);
				border.setStrokeWidth(1);
			}
	
		});
		
	}

	public void setInsect(Insect insect) {
		this.insect = insect;
		
		img = this.insect.getImage();
    	imv = new ImageView(img);
    	imv.setFitHeight(Config.TILE_W);
    	imv.setPreserveRatio(true);
//    	if (this.insect.getDirection() == enmDirection.left) {
//    		imv.setRotate(imv.getRotate() + 90);
//    	} else {
//    		imv.setRotate(imv.getRotate() + -90);
//    	}
    	getChildren().add(imv);
	}
	
	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}
	
}
