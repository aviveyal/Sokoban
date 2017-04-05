package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import Database.LevelsDB;
import Database.Scores;
import Database.SokobanDBManager;
import Database.Users;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopUpController implements Initializable {


	@FXML
	private Label levelname;
	@FXML
	private Label stepsresult;
	@FXML
	private Label timeresult;
	@FXML
	private TextField name;
	@FXML
	private Button buttonsign;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
 
	
	}
	
	public void Signscore(){
		String time = timeresult.getText();
		String steps = stepsresult.getText();
		SokobanDBManager DB= new SokobanDBManager();
		
				
		Users u = DB.addUser(name.getText()); //check if exists before adding
				
		LevelsDB l = DB.addLevel(levelname.getText());
		
		DB.addScores(u,l,time,steps);
		//run on the data base check if exist 
		
		Stage stage = (Stage) buttonsign.getScene().getWindow();
		stage.close();
		
	}
	public void setStepsresult(String s) {
		stepsresult.setText(s);
	}
	public void settimeresult(String s) {
		timeresult.setText(s);
	}
	public void setlevelname(String s) {
		levelname.setText(s);
	}
}
