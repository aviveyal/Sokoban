package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import Database.Scores;
import Database.SokobanDBManager;
import controller.SokobanController;
import view.SokobanLevelDisplayer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
import javafx.stage.Stage;
import model.MyModel;
import model.Data.Level;

public class MainWindowController extends Observable implements Initializable, View {

	@FXML
	Label keyInputLabelup;
	@FXML
	Label keyInputLabeldown;
	@FXML
	Label keyInputLabelleft;
	@FXML
	Label keyInputLabelright;
	@FXML
	Button button1;
	@FXML
	Label levelname;

	public SokobanDBManager DB;
	// sent new keys

	public Button getButton1() {
		return button1;
	}

	@FXML
	public void sethandleup(KeyEvent key) {
		keyInputLabelup.setText("" + key.getCode());
	}

	@FXML
	public void sethandledown(KeyEvent key) {
		keyInputLabeldown.setText("" + key.getCode());
	}

	@FXML
	public void sethandleleft(KeyEvent key) {
		keyInputLabelleft.setText("" + key.getCode());
	}

	@FXML
	public void sethandleupright(KeyEvent key) {
		keyInputLabelright.setText("" + key.getCode());
	}

	@FXML
	SokobanLevelDisplayer SokobanLevelDisplayer;

	@FXML
	Text status;

	@FXML
	Text timetext;

	@FXML
	Text steps;

	Level level;
	int stepCounter;
	int countSec;
	int countMin;
	boolean loadedlevel = false;
	boolean timerun = false;
	File chosen;

	StringProperty finalTime;
	SimpleStringProperty finalsteps;

	public MainWindowController() {

		DB = new SokobanDBManager();
		finalTime = new SimpleStringProperty();
		finalsteps = new SimpleStringProperty();

		Timer t = new Timer();

		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (loadedlevel && timerun) {

					if (countSec == 59) {
						countMin++;
						countSec = 0;
					} else
						countSec++;
				}
				if (countSec < 10)
					finalTime.set("" + countMin + ":" + "0" + countSec);
				else
					finalTime.set("" + countMin + ":" + "" + countSec);

			}
		}, 0, 1000);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		keyInputLabelup.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> keyInputLabelup.requestFocus());
		keyInputLabeldown.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> keyInputLabeldown.requestFocus());
		keyInputLabelleft.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> keyInputLabelleft.requestFocus());
		keyInputLabelright.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> keyInputLabelright.requestFocus());

		SokobanLevelDisplayer.requestFocus();
		SokobanLevelDisplayer.addEventFilter(MouseEvent.ANY, (e) -> SokobanLevelDisplayer.requestFocus());

		SokobanLevelDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override

			public void handle(KeyEvent event) {

				List<String> params = new LinkedList<String>();
				if (loadedlevel && timerun) {
					if (event.getCode().toString().equals(keyInputLabelup.getText())) {
						params.add("move");
						params.add("up");
					} else if (event.getCode().toString().equals(keyInputLabeldown.getText())) {

						params.add("move");
						params.add("down");

					} else if (event.getCode().toString().equals(keyInputLabelleft.getText())) {

						params.add("move");
						params.add("left");

					} else if (event.getCode().toString().equals(keyInputLabelright.getText())) {

						params.add("move");
						params.add("right");
					}

					if (params.size() == 2)// mean that character tried move
					{
						finalsteps.set("" + (stepCounter++));

						setChanged();
						notifyObservers(params);
					}

				}

			}

		});

	}

	public void OpenFile() {
		List<String> params = new LinkedList<String>();
		FileChooser fc = new FileChooser();
		fc.setTitle("open Level file");
		fc.setInitialDirectory(new File("./levels"));
		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Level files", "*.txt", "*.obj",
				"*.xml");
		fc.getExtensionFilters().add(extFilter);
		chosen = fc.showOpenDialog(null);

		if (chosen != null) {
			timerun = true;
			countMin = 0;
			countSec = 0;
			stepCounter = 1;
			timetext.textProperty().bind(finalTime);
			steps.textProperty().bind(finalsteps);
			finalsteps.set("" + 0);
			status.textProperty().set("Good Luck!");

			loadedlevel = true;
			params.add("load");
			params.add("levels/" + chosen.getName());
			setChanged();
			notifyObservers(params);
			button1.setVisible(false);
			SokobanLevelDisplayer.setVisible(true);
			timetext.setVisible(true);
			steps.setVisible(true);
			levelname.setVisible(true);
			levelname.setText(chosen.getName().substring(0, chosen.getName().length() - 4));
		}

	}

	public void SaveFile() {
		List<String> params = new LinkedList<String>();
		FileChooser fc = new FileChooser();
		fc.setTitle("save Level file");
		fc.setInitialDirectory(new File("./levels"));

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Level files", "*.txt", "*.obj",
				"*.xml");

		fc.getExtensionFilters().add(extFilter);
		File chosen = fc.showSaveDialog(null);

		if (chosen != null) {
			params.add("save");
			params.add("levels/" + chosen.getName());
			setChanged();
			notifyObservers(params);
		}

	}

	public void Exit() {
		List<String> params = new LinkedList<String>();
		params.add("exit");
		setChanged();
		notifyObservers(params);
	}

	@Override
	public void mDisplayCommand(Level level) throws IOException {

		SokobanLevelDisplayer.setLevelData(level); // sends current level data
		SokobanLevelDisplayer.redraw();

		checkfinish();

	}

	public void checkfinish() throws IOException {

		/* check if finished the level */
		int counter = 0;
		for (int i = 0; i < SokobanLevelDisplayer.level.getBoxes().size(); i++) {
			for (int j = 0; j < SokobanLevelDisplayer.level.getBoxOnTareget().size(); j++) {
				if ((SokobanLevelDisplayer.level.getBoxes().get(i).getX() == SokobanLevelDisplayer.level
						.getBoxOnTareget().get(j).getX())
						&& (SokobanLevelDisplayer.level.getBoxes().get(i).getY() == SokobanLevelDisplayer.level
						.getBoxOnTareget().get(j).getY())) {
					counter++;
					j = SokobanLevelDisplayer.level.getBoxOnTareget().size();
				}
			}
		}

		if (counter == SokobanLevelDisplayer.level.getBoxes().size()) {

			status.textProperty().set("Do you want save your scores?");
			timerun = false;
			button1.setVisible(true);
			button1.setFocusTraversable(true);

		}

	}

	public void Restart() {
		List<String> params = new LinkedList<String>();

		if (chosen != null) {
			timerun = true;
			countMin = 0;
			countSec = 0;
			stepCounter = 1;
			timetext.textProperty().bind(finalTime);
			steps.textProperty().bind(finalsteps);
			finalsteps.set("" + 0);
			status.textProperty().set("Good Luck!");

			loadedlevel = true;
			params.add("load");
			params.add("levels/" + chosen.getName());
			setChanged();
			notifyObservers(params);
			button1.setVisible(false);
			SokobanLevelDisplayer.setVisible(true);
			timetext.setVisible(true);
			steps.setVisible(true);
			levelname.setVisible(true);

		}

	}

	public void onChangeButtonAction() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
		BorderPane root = (BorderPane) loader.load();
		Scene scene = new Scene(root, 360, 300);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.setTitle("Sokoban - Aviv Eyal");
		newStage.show();
		PopUpController p = (PopUpController) loader.getController();
		p.setStepsresult(finalsteps.get().toString());
		p.settimeresult(finalTime.get().toString());
		p.setlevelname(levelname.getText());

		// restart first page
		Restart();
		timerun = false;
		SokobanLevelDisplayer.setVisible(false);
		timetext.setVisible(false);
		steps.setVisible(false);
		status.textProperty().set("Open a level from the menu and start play!");
		levelname.setVisible(false);

	}

	public void Top() throws IOException {

		ArrayList<String> list = new ArrayList<>();
		if ((levelname.isVisible()) && (!levelname.getText().equals("Level name"))) {
			ArrayList<Scores> scores = DB.showTopTen(levelname.getText());
			list = DB.display(scores);
		}
		topStage(list);

	}

	public ArrayList<String> TopTime(String levelname) throws IOException {

		ArrayList<String> list = new ArrayList<>();

		ArrayList<Scores> scores = DB.showTopTen(levelname);
		list = DB.display(scores);

		return list;

	}

	public ArrayList<String> Topsteps(String levelname) throws IOException {

		ArrayList<String> list = new ArrayList<>();

		ArrayList<Scores> scores = DB.showTopTenSteps(levelname);
		list = DB.display(scores);

		return list;

	}

	public ArrayList<String> TopTimeUser(String username) throws IOException {

		ArrayList<String> list = new ArrayList<>();

		ArrayList<Scores> scores = DB.showTopTenUser(username);
		list = DB.displayUser(scores);

		return list;

	}

	public ArrayList<String> TopstepsUser(String username) throws IOException {

		ArrayList<String> list = new ArrayList<>();

		ArrayList<Scores> scores = DB.showTopTenUserSteps(username);
		list = DB.displayUser(scores);

		return list;

	}

	public ArrayList<String> Toplex(String username) throws IOException {

		ArrayList<String> list = new ArrayList<>();

		ArrayList<Scores> scores = DB.showTopTenUserlex(username);
		list = DB.displayUserlex(scores);

		return list;

	}

	public void topStage(ArrayList<String> list) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Top.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Scene scene = new Scene(root, 460, 370);
		Stage newStage = new Stage();
		newStage.setScene(scene);
		newStage.setTitle("Sokoban - Aviv Eyal");
		newStage.show();

		TopController Top = (TopController) loader.getController();
		Top.setList(list);
		if ((levelname.isVisible()) && (!levelname.getText().equals("Level name"))) {

			Top.setText(levelname.getText());
		}
	}

}
