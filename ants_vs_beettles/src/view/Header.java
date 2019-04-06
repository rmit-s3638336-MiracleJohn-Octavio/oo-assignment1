package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Header extends HBox {

	private static final int HEADER_HEIGHT = 80;
	
	public Header() {
		setPrefHeight(HEADER_HEIGHT);
		setStyle("-fx-background-color: derive(" + "#3b5998" + ", 0%)");
		
		// Add Children			
        Image img = new Image("/assets/antsvsbugs-logo.png",200,150,true,true);
        ImageView imv = new ImageView(img);
        setPadding(new Insets(10, 5, 10, 15));
        setAlignment(Pos.BOTTOM_LEFT);
        getChildren().add(imv);	
	}

	public Header(double spacing) {
		super(spacing);
		// TODO Auto-generated constructor stub
	}

	public Header(Node... children) {
		super(children);
		// TODO Auto-generated constructor stub
	}

	public Header(double spacing, Node... children) {
		super(spacing, children);
		// TODO Auto-generated constructor stub
	}

}
