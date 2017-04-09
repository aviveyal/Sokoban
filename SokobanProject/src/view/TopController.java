package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ListSelectionModel;

import org.hibernate.dialect.identity.SybaseAnywhereIdentityColumnSupport;

import Database.Scores;
import Database.SokobanDBManager;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TopController implements Initializable, TopTen {

	@FXML
	private Label label;
	@FXML
	private TableView<LevelScore> tablelist;
	@FXML
	private TableColumn<LevelScore, String> nameColumn = new TableColumn<>("username");
	@FXML
	private TableColumn<LevelScore, String> timeColumn = new TableColumn<>("time");
	@FXML
	private TableColumn<LevelScore, String> stepsColumn = new TableColumn<>("steps");
	@FXML
	private TextField text;
	@FXML
	private Label error;

	SokobanDBManager DB;
	MainWindowController main;
	UserViewController userview;

	public class LevelScore {

		String username;
		String time;
		int steps;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
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

		public LevelScore(String username, String time, int steps) {
			this.username = username;
			this.time = time;
			this.steps = steps;
		}

		public LevelScore() {
			super();
		}

	}

	ObservableList<LevelScore> listItems1 = FXCollections.observableArrayList();

	public TextField getText() {
		return text;
	}

	public void setText(String text) {
		this.text.setText(text);
	}

	public void setList(ArrayList<String> scoreslist) {

		this.tablelist = new TableView<>();

		for (int i = 0; i < scoreslist.size(); i++) {

			String arr[] = scoreslist.get(i).split(",");
			this.listItems1.add(new LevelScore(arr[0], arr[1], Integer.parseInt(arr[2])));
		}

	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(String s) {
		label.setText(s);
	}

	public TopController() {

		this.DB = new SokobanDBManager();
		this.main = new MainWindowController();
		this.tablelist = new TableView<>();

	}

	public void stepssort() throws IOException {
		if (text.getText() != null) { // have to check if exist

			ArrayList<String> list = main.Topsteps(text.getText());

			this.listItems1.clear();
			for (int i = 0; i < list.size(); i++) {

				String arr[] = list.get(i).split(",");
				this.listItems1.add(new LevelScore(arr[0], arr[1], Integer.parseInt(arr[2])));
			}

		}

	}

	public void timesort() throws IOException {

		if (text.getText() != null) {// have to check if exist

			ArrayList<String> list = main.TopTime(text.getText());

			this.listItems1.clear();
			for (int i = 0; i < list.size(); i++) {

				String arr[] = list.get(i).split(",");
				this.listItems1.add(new LevelScore(arr[0], arr[1], Integer.parseInt(arr[2])));
			}
		}

	}

	public void search() {
		if (DB.levelexists(text.getText())) {
			ArrayList<Scores> scores = DB.showTopTen(text.getText());
			ArrayList<String> arrlist = DB.display(scores);

			this.listItems1.clear();

			for (int i = 0; i < arrlist.size(); i++) {

				String arr[] = arrlist.get(i).split(",");

				this.listItems1.add(new LevelScore(arr[0], arr[1], Integer.parseInt(arr[2])));

			}
			error.setVisible(false);
		} else
			error.setVisible(true);

	}

	public void userview() throws IOException {
		// close current stage - close current level Scores and switch to scores
		// by users
		Stage stage = (Stage) text.getScene().getWindow();
		stage.close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("userview.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Scene scene = new Scene(root, 460, 370);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.setTitle("Sokoban - Aviv Eyal");
		newStage.show();
		userview = (UserViewController) loader.getController();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		stepsColumn.setCellValueFactory(new PropertyValueFactory<>("steps"));

		tablelist.setItems(listItems1);

		// open the user view scores when clicked on his name
		tablelist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		tablelist.getSelectionModel().clearSelection();
		tablelist.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Integer> c) {
				try {
					 
					userview();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				userview.setText(nameColumn.getCellData(c.getList().get(0)).toString());
				userview.search();
				
			}

		});

	}

}
