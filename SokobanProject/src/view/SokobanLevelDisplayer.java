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
import model.Data.Box;
import model.Data.BoxOntarget;
import model.Data.Level;
import model.Data.SokoChar;
import model.Data.Target;
import model.Data.Wall;
import model.modelCommands.Display;
import model.modelCommands.Load;

/**
 * 
 * @author Aviv Eyal Responsible of the view of the level board in the gui ,
 *         loads pictures from resources folder and load them
 */
public class SokobanLevelDisplayer extends Canvas {

	
	char[][] LevelData;
	int maxrow;
	int maxcol;

	private StringProperty wallFilename;
	private StringProperty charFilename;
	private StringProperty boxFilename;
	private StringProperty targetFilename;
	private StringProperty boxontargetFilename;

	public void setLevelData(char[][] LevelData,int maxrow ,int maxcol) {
		this.LevelData = LevelData;
		this.maxrow = maxrow;
		this.maxcol = maxcol;
	}

	public SokobanLevelDisplayer() {
		wallFilename = new SimpleStringProperty();
		charFilename = new SimpleStringProperty();
		boxFilename = new SimpleStringProperty();
		targetFilename = new SimpleStringProperty();
		boxontargetFilename = new SimpleStringProperty();

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
			double w = W / maxcol;
			double h = H / maxrow;

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
			gc.beginPath();
			gc.clearRect(0, 0, W, H);
			

			for (int i = 0; i < maxrow; i++)
				for (int j = 0; j < maxcol; j++) {

					if (LevelData[i][j] == Wall.wallsymbol)
						gc.drawImage(wall, j * w, i * h, w, h);
					else if (LevelData[i][j] == Box.boxessymbol)
						gc.drawImage(box, j * w, i * h, w, h);
					else if (LevelData[i][j] == Target.targetsymbol)
						gc.drawImage(target, j * w, i * h, w, h);
					else if (LevelData[i][j] == SokoChar.sokosymbol)
						gc.drawImage(CharacterSoko, j * w, i * h, w, h);
					else if (LevelData[i][j] == BoxOntarget.boxesontargetsymbol)
						gc.drawImage(boxontarget, j * w, i * h, w, h);
					else if (LevelData[i][j] == ' ')
						gc.setFill(null);
					
					
				}
			

		}
	}

}