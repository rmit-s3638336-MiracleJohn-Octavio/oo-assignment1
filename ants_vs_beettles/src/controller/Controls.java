package controller;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;

public class Controls {
	
	/*+--------+*
	 *| Alerts |
	 *+--------+*/
	
	public static void myAlert(String title, String header, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
	    	alert.setTitle(title);
	    	Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        alert.setX(bounds.getMaxX() / 4);
        alert.setY(bounds.getMaxY() / 5);
	    	alert.setHeaderText(header);
	    	alert.setContentText(message);
	    	alert.showAndWait();
	}
	public static void myAlert(String message) {
		myAlert("Message Alert","Header",message, AlertType.INFORMATION);
	}
	public void myAlert() {
		myAlert("Message Alert","Header","This is a dummy alert!", AlertType.INFORMATION);
	}
	
}
