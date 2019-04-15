package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.insect.Insect;

import java.io.IOException;
import controller.TileVC;

public class DashboardVC extends BorderPane {

	@FXML
	public BorderPane dashboard; 
	
	@FXML
	public VBox vbxPanelLeft;

	@FXML
	public VBox vbxPanelRight;
	
	@FXML
	public Text txtTest;
	
	private String TextValue;
	
	public void initialize() throws IOException {
		
		Pane tmp = new Pane();
		tmp.setStyle("-fx-background-color: blue");
		dashboard.setCenter(tmp);
		
    }
	
	// Events
	
	@FXML
	public void topPane_clicked(MouseEvent event) {
		Helper.printMe("Clicked!");
	}
	
	public BorderPane getDashboard() {
		return dashboard;
	}

	public VBox getVbxPanelLeft() {
		return vbxPanelLeft;
	}
	
	public VBox getVbxPanelRight() {
		return vbxPanelRight;
	}

	public Text getTxtTest() {
		return txtTest;
	}

	public void setTxtTest(Text txtTest) {
		this.txtTest = txtTest;
	}

	public String getTextValue() {
		return TextValue;
	}

	public void setTextValue(String textValue) {
		TextValue = textValue;
		txtTest.setText(textValue);
	}

}
