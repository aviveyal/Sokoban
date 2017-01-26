package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.Data.Level;
import model.modelCommands.Display;
import model.modelCommands.Load;

public class SokobanLevelDisplayer extends Canvas {

	Level level;
	String[][] LevelData;

	private StringProperty wallFilename;
	private StringProperty charFilename;
	private StringProperty boxFilename;
	private StringProperty targetFilename;
	private StringProperty boxontargetFilename;

	public void setLevelData(Level level) {
		this.LevelData = level.makestring();
		this.level = level; // for maxrow and maxcolumn functions

	}

	public SokobanLevelDisplayer() {
		wallFilename = new SimpleStringProperty();
		charFilename = new SimpleStringProperty();
		boxFilename = new SimpleStringProperty();
		targetFilename = new SimpleStringProperty();
		boxontargetFilename = new SimpleStringProperty();

	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getCharFilename() {
		return charFilename.get();
	}

	public void setCharFilename(String charFilename) {
		this.charFilename.set(charFilename);

	}

	public String getWallFilename() {
		return wallFilename.get();
	}

	public void setWallFilename(String wallFilename) {
		this.wallFilename.set(wallFilename);
	}

	public String getBoxFilename() {
		return boxFilename.get();
	}

	public void setBoxFilename(String boxFilename) {
		this.boxFilename.set(boxFilename);
	}

	public String getTargetFilename() {
		return targetFilename.get();
	}

	public void setTargetFilename(String targetFilename) {
		this.targetFilename.set(targetFilename);
	}

	public String getBoxontargetFilename() {
		return boxontargetFilename.get();
	}

	public void setBoxontargetFilename(String boxontargetFilename) {
		this.boxontargetFilename.set(boxontargetFilename);
	}

	public void redraw() {

		if (LevelData != null) {
			double W = getWidth();
			double H = getHeight();
			double w = W / level.maxcolumnsize();
			double h = H / level.maxrowsize();

			GraphicsContext gc = getGraphicsContext2D();
			
			
			Image CharacterSoko = null;
			Image wall = null;
			Image box = null;
			Image target = null;
			Image boxontarget = null;

			try {
				CharacterSoko = new Image(new FileInputStream(charFilename.get()));
				wall = new Image(new FileInputStream(wallFilename.get()));
				box = new Image(new FileInputStream(boxFilename.get()));
				target = new Image(new FileInputStream(targetFilename.get()));
				boxontarget = new Image(new FileInputStream(boxontargetFilename.get()));

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			gc.clearRect(0, 0, W, H);

			for (int i = 0; i <= level.maxrowsize(); i++)
				for (int j = 0; j <= level.maxcolumnsize(); j++) {

					if (LevelData[i][j].charAt(0) == '#')
						gc.drawImage(wall, j * w, i * h, w, h);
					else if (LevelData[i][j].charAt(0) == '@')
						gc.drawImage(box, j * w, i * h, w, h);
					else if (LevelData[i][j].charAt(0) == 'o')
						gc.drawImage(target, j * w, i * h, w, h);
					else if (LevelData[i][j].charAt(0) == 'A')
						gc.drawImage(CharacterSoko, j * w, i * h, w, h);
					else if (LevelData[i][j].charAt(0) == 'V')
						gc.drawImage(boxontarget, j * w, i * h, w, h);
					else if (LevelData[i][j].charAt(0) == ' ')
						gc.setFill(null);

				}

		}
	}

}