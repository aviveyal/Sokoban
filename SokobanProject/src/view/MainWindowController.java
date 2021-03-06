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
import controller.common.common;
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

/**
 * 
 * @author Aviv Eyal main control of the GUI ! implements everything that exists
 *         on gui also observable and possible connect with the controller to
 *         execute commands and connect to the model to send data
 */
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
	// common commonlevel;
	boolean solver = false; // check if player moved
	int stepCounter;
	int countSec;
	int countMin;
	String solution = "";
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
						solver = true; // cant solve automaticly
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
			solver = false;
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

		SokobanLevelDisplayer.setLevelData(level.makestring(), level.maxrowsize(), level.maxcolumnsize()); // sends
																											// current
																											// level
																											// data
		SokobanLevelDisplayer.redraw();

		checkfinish(level);

	}

	public void checkfinish(Level level) throws IOException {

		/* check if finished the level */
		int counter = 0;
		for (int i = 0; i < level.getBoxes().size(); i++) {
			for (int j = 0; j < level.getBoxOnTareget().size(); j++) {
				if ((level.getBoxes().get(i).getX() == level.getBoxOnTareget().get(j).getX())
						&& (level.getBoxes().get(i).getY() == level.getBoxOnTareget().get(j).getY())) {
					counter++;
					j = level.getBoxOnTareget().size();
				}
			}
		}

		if (counter == level.getBoxes().size()) {

			status.textProperty().set("Do you want save your scores?");
			timerun = false;
			button1.setVisible(true);
			button1.setFocusTraversable(true);

		}

	}

	public void Restart() throws IOException {
		List<String> params = new LinkedList<String>();

		if (chosen != null) {
			timerun = true;
			solver = false;
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
			// SokobanLevelDisplayer.setVisible(true);
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

		System.out.println(list.get(0).toString());
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

	public void Hint() {
		if (!solver) {
			List<String> params = new LinkedList<String>();
			params.add("solve");
			params.add(levelname.getText());
			setChanged();
			notifyObservers(params);
		} else
			status.textProperty().set("You need to Restart Level");

	}

	public void Solution() {
		if (!solver) {
			List<String> params = new LinkedList<String>();
			params.add("solve");
			params.add(levelname.getText());
			setChanged();
			notifyObservers(params);

			// wait for solution from server
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Timer t2 = new Timer();
			if(solution!=null){
			int sollentgh = solution.length();

			t2.scheduleAtFixedRate(new TimerTask() {
				int i = 0;

				@Override
				public void run() {
					params.clear();
					if (i < sollentgh) {
						switch (solution.charAt(i)) {
						case 'U':
							params.add("move");
							params.add("up");
						case 'D':
							params.add("move");
							params.add("down");
						case 'L':
							params.add("move");
							params.add("left");
						case 'R':
							params.add("move");
							params.add("right");

						}
						i++;

						finalsteps.set("" + (stepCounter++));

						setChanged();
						notifyObservers(params);
					}
					if (i == sollentgh)
						t2.cancel();
				}
			}, 0, 500);
			}
		} else
			status.textProperty().set("You need to Restart Level");
	}

	@Override
	public void getSolution(String solution) {
		this.solution = solution;
		System.out.println("got the solution :" + solution);
		status.textProperty().set("Hint: " + solution);
	}

}
