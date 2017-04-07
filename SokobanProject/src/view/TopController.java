package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.activity.ActivityCompletedException;

import org.hibernate.dialect.identity.SybaseAnywhereIdentityColumnSupport;

import Database.Scores;
import Database.SokobanDBManager;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TopController implements  Initializable,TopTen  {

	
	@FXML
	private Label label;
	@FXML
	private ListView<String> list;
	@FXML 
	private TextField text;
	@FXML 
	private Label error;
	
	SokobanDBManager DB ;
	MainWindowController main;
	UserViewController userview;

	final ObservableList<String> listItems = FXCollections.observableArrayList();  
	
	public ListView<String> getList() {
		return list;
	}



	public TextField getText() {
		return text;
	}



	public void setText(String text) {
		this.text.setText(text);
	}



	public void setList(ArrayList <String> scoreslist) {
		this.list = new ListView<>();
		
		
		for(int i=0 ;i <scoreslist.size();i++)
		{
		this.listItems.add(i,scoreslist.get(i));
		}
		
	}



	public Label getLabel() {
		return label;
	}



	public void setLabel(String s) {
		label.setText(s); 
	}



	public TopController() {
		this.list = new ListView<>();
		this.DB = new SokobanDBManager();
		this.main = new MainWindowController();
		
		
	}

	public void stepssort() throws IOException{
		if (text.getText()!=null) { //have to check if exist
			//MainWindowController main = new MainWindowController();
			
			ArrayList<String> list = main.Topsteps(text.getText());

			this.listItems.clear();

			for (int i = 0; i < list.size(); i++) {
				this.listItems.add(i, list.get(i));
			}

		}
		
		
	}
	public void timesort() throws IOException{

		if (text.getText()!=null) {//have to check if exist
		//	MainWindowController main = new MainWindowController();

			ArrayList<String> list = main.TopTime(text.getText());
			this.listItems.clear();

			for (int i = 0; i < list.size(); i++) {
				this.listItems.add(i, list.get(i));
			}
		}
				
	}
	
	public void search()
	{
		//SokobanDBManager DB = new SokobanDBManager();
		if(DB.levelexists(text.getText()))
		{
			ArrayList<Scores>scores =  DB.showTopTen(text.getText());
			ArrayList<String> arrlist = DB.display(scores);
			this.listItems.clear();
			
			for (int i = 0; i < arrlist.size(); i++) {
				this.listItems.add(i, arrlist.get(i));
			}
			error.setVisible(false);
		}
		else
			error.setVisible(true);
			
	}
	public void userview() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("userview.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Scene scene = new Scene(root,460,370);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.setTitle("Sokoban - Aviv Eyal");
		newStage.show();
		userview = (UserViewController) loader.getController();

	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	
		list.setItems(listItems);
		
		list.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				//get the name of the user from the list 
				String str = observable.toString();
				String arr[] = str.split(",", 3);
				String arr2[] =arr[2].split("\t", 2);
				String[] name =arr2[1].split(" ",2);
				String username= name[0];
				try {
					userview();
					userview.setText(username);
					userview.search();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});

		    
			
		
	}
	
}
