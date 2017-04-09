package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Database.Scores;
import Database.SokobanDBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import view.TopController.LevelScore;


public class UserViewController implements Initializable, TopTen {
	@FXML
	private TableView<UserScore> tablelist;
	@FXML
	private TableColumn<UserScore, String> nameColumn = new TableColumn<>("levelname");
	@FXML
	private TableColumn<UserScore, String> timeColumn = new TableColumn<>("time");
	@FXML
	private TableColumn<UserScore, String> stepsColumn = new TableColumn<>("steps");
	@FXML
	private TextField text;
	@FXML
	private Label error;

	
	
	
	SokobanDBManager DB;
	MainWindowController main;


	
public class UserScore{
	
	String levelname;
	String time;
	int steps;
	public String getLevelname() {
		return levelname;
	}
	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public UserScore(String levelname, String time, int steps) {
		super();
		this.levelname = levelname;
		this.time = time;
		this.steps = steps;
	}
	public UserScore() {
		super();
	}
	
}
	
	ObservableList<UserScore> listItems1 = FXCollections.observableArrayList();

	public ObservableList<UserScore> getListItems1() {
		return listItems1;
	}

	public void setListItems1(ObservableList<UserScore> listItems1) {
		this.listItems1 = listItems1;
	}
	
	public TextField getText() {
		return text;
	}

	public void setText(String text) {
		this.text.setText(text);
	}

	public void setList(ArrayList<String> scoreslist) {
		this.tablelist = new TableView<>();

		for (int i = 0; i < scoreslist.size(); i++) {
			String arr[]=scoreslist.get(i).split(",");
			this.listItems1.add(new UserScore(arr[0],arr[1],Integer.parseInt(arr[2])));
		}

	}

	public UserViewController() {
		this.tablelist = new TableView<>();
		this.DB = new SokobanDBManager();
		this.main = new MainWindowController();

	}





	public void lexSort() throws IOException {

		if (text.getText() != null) { // have to check if exist

			ArrayList<String> list = main.Toplex(text.getText());

			this.listItems1.clear();

			for (int i = 0; i < list.size(); i++) {
				String arr[]=list.get(i).split(",");
				this.listItems1.add(new UserScore(arr[0],arr[1],Integer.parseInt(arr[2])));
			}

		}
	}

	@Override
	public void stepssort() throws IOException {

		if (text.getText() != null) { // have to check if exist

			ArrayList<String> list = main.TopstepsUser(text.getText());

			this.listItems1.clear();

			for (int i = 0; i < list.size(); i++) {
				String arr[]=list.get(i).split(",");
				this.listItems1.add(new UserScore(arr[0],arr[1],Integer.parseInt(arr[2])));
				
			}

		}
	}

	@Override
	public void timesort() throws IOException {
		if (text.getText() != null) {// have to check if exist

			ArrayList<String> list = main.TopTimeUser(text.getText());
			this.listItems1.clear();

			for (int i = 0; i < list.size(); i++) {
				String arr[]=list.get(i).split(",");
				this.listItems1.add(new UserScore(arr[0],arr[1],Integer.parseInt(arr[2])));
			}
		}

	}

	@Override
	public void search() {
		if (DB.userexists(text.getText())) {
			ArrayList<Scores> scores = DB.showTopTenUser(text.getText());
			ArrayList<String> arrlist = DB.displayUser(scores);
			this.listItems1.clear();

			for (int i = 0; i < arrlist.size(); i++) {
				String arr[]=arrlist.get(i).split(",");
				this.listItems1.add(new UserScore(arr[0],arr[1],Integer.parseInt(arr[2])));
			
			}
			error.setVisible(false);
		} else
			error.setVisible(true);
	}

	public void initialize(URL location, ResourceBundle resources) {
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("levelname"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		stepsColumn.setCellValueFactory(new PropertyValueFactory<>("steps"));
		
		tablelist.setItems(getListItems1());
	
		
	}

	
}
