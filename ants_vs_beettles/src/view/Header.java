package view;

import controller.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Header extends HBox {

	public Header() {
		setPrefHeight(Config.HEADER_HEIGHT);
		System.out.println(Config.HEADER_HEIGHT);
		setStyle("-fx-background-color: derive(" + "#3b5998" + ", 0%)");
		
		// Add Children			
        Image img = new Image("/assets/antsvsbugs-logo.png",10, Config.HEADER_HEIGHT,true,true);
        ImageView imv = new ImageView(img);
        setPadding(new Insets(0, 0, 0, 0));
        setAlignment(Pos.BOTTOM_LEFT);
        getChildren().add(imv);	
	}

}
