package view;

import java.net.URL;
import java.util.ResourceBundle;

import boot.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PopUpController implements Initializable {


	
	@FXML
	private Label stepsresult;
	@FXML
	private Label timeresult;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
 
	
	}

	public void setStepsresult(String s) {
		stepsresult.setText(s);
	}
	public void settimeresult(String s) {
		timeresult.setText(s);
	}
}
