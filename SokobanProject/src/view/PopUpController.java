package view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		
		//use existing data from DB 
		Users u = new Users(name.getText());
		DB.addUser(u);
		LevelsDB l = new LevelsDB("level1blabla");
		DB.addLevel(l);
		Scores s = new Scores(u, l,time,steps);
		DB.addScores(s);
		
		
			
				
		if (Users.class.getName().equals(name.getText()))
				{
				System.out.println("ok");
				}
	
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
}
