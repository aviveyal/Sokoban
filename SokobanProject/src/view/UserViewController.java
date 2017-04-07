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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UserViewController implements Initializable, TopTen {

	@FXML
	private ListView<String> list;
	
	@FXML
	private TextField text;
	@FXML
	private Label error;

	SokobanDBManager DB;
	MainWindowController main;

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

	public void setList(ArrayList<String> scoreslist) {
		this.list = new ListView<>();

		for (int i = 0; i < scoreslist.size(); i++) {
			this.listItems.add(i, scoreslist.get(i));
		}

	}

	public UserViewController() {
		this.list = new ListView<>();
		this.DB = new SokobanDBManager();
		this.main = new MainWindowController();

	}



	public void initialize(URL location, ResourceBundle resources) {
		list.setItems(listItems);
		
	}

	public void lexSort() throws IOException {

		if (text.getText() != null) { // have to check if exist
			// MainWindowController main = new MainWindowController();

			ArrayList<String> list = main.Toplex(text.getText());

			this.listItems.clear();

			for (int i = 0; i < list.size(); i++) {
				this.listItems.add(i, list.get(i));
			}

		}
	}

	@Override
	public void stepssort() throws IOException {

		if (text.getText() != null) { // have to check if exist
			// MainWindowController main = new MainWindowController();

			ArrayList<String> list = main.TopstepsUser(text.getText());

			this.listItems.clear();

			for (int i = 0; i < list.size(); i++) {
				this.listItems.add(i, list.get(i));
			}

		}
	}

	@Override
	public void timesort() throws IOException {
		if (text.getText() != null) {// have to check if exist
			// MainWindowController main = new MainWindowController();

			ArrayList<String> list = main.TopTimeUser(text.getText());
			this.listItems.clear();

			for (int i = 0; i < list.size(); i++) {
				this.listItems.add(i, list.get(i));
			}
		}

	}

	@Override
	public void search() {
		if (DB.userexists(text.getText())) {
			ArrayList<Scores> scores = DB.showTopTenUser(text.getText());
			ArrayList<String> arrlist = DB.displayUser(scores);
			this.listItems.clear();

			for (int i = 0; i < arrlist.size(); i++) {
				this.listItems.add(i, arrlist.get(i));
			}
			error.setVisible(false);
		} else
			error.setVisible(true);
	}

}
