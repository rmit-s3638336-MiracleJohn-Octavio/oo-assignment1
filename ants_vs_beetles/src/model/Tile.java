package model;

import controller.Config;
import controller.Config.enmDirection;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
	
	private Text text = new Text();
	private Rectangle border = new Rectangle(Config.TILE_W, Config.TILE_H);
	private Image img;
	private ImageView imv;
	private Insect insect;
	
	public Insect getInsect() {
		return insect;
	}

	public void setInsect(Insect insect) {
		this.insect = insect;
		
		img = this.insect.image;
    	imv = new ImageView(img);
    	imv.setFitHeight(Config.TILE_W);
    	imv.setPreserveRatio(true);
    	if (this.insect.getDirection() == enmDirection.left) {
    		imv.setRotate(imv.getRotate() + 90);
    	} else {
    		imv.setRotate(imv.getRotate() + -90);
    	}
    	getChildren().add(imv);
	}
	
	public Tile(String value) {
		
        border.setFill(null);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(1);
        
        img = new Image("/assets/ant-red.png",200,150,true,true);
    	imv = new ImageView();

        text.setText(value);
        text.setFont(Font.font(30));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text, imv);
        
        setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) {
				if (Config.selectedInsect == null) {
					if (insect != null) {
						Config.selectedInsect = insect;
					}
				} else {
					insect = Config.selectedInsect;
					System.out.println(insect);
					setInsect(insect);
					
					// Reset
					Config.selectedInsect = null;
				}
			}
	
		});
        
        setOnMouseMoved(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) {
				border.setStroke(Color.RED);
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

	public String getText() {
		return text.getText();
	}

	public void setText(String value) {
		this.text.setText(value);
	}
	
	
}
