package view;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import view.SokobanLevelDisplayer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.text.Text;
import javafx.stage.FileChooser;

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

	// sent new keys

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

	StringProperty finelTime;
	SimpleStringProperty finalsteps;

	public MainWindowController() {

		finelTime = new SimpleStringProperty();
		finalsteps = new SimpleStringProperty();

		Timer t = new Timer();

		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (loadedlevel) {

					if (countSec == 59) {
						countMin++;
						countSec = 0;
					} else
						countSec++;
				}
				if (countSec < 10)
					finelTime.set("" + countMin + ":" + "0" + countSec);
				else
					finelTime.set("" + countMin + ":" + "" + countSec);

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
				if (loadedlevel) {
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
		File chosen = fc.showOpenDialog(null);

		if (chosen != null) {
			countMin = 0;
			countSec = 0;
			stepCounter = 1;
			timetext.textProperty().bind(finelTime);
			steps.textProperty().bind(finalsteps);
			finalsteps.set("" + 0);
			status.textProperty().set("Good Luck!");

			loadedlevel = true;
			params.add("load");
			params.add("levels/" + chosen.getName());
			setChanged();
			notifyObservers(params);

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
	public void mDisplayCommand(Level level) {

		SokobanLevelDisplayer.setLevelData(level); // sends current level data
		SokobanLevelDisplayer.redraw();

	}

	
}
