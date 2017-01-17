package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class SokobanLevelDisplayer extends Canvas {

	int[][] LevelData;

	private StringProperty wallFilename;
	private StringProperty charFilename;
	int cCol ,cRow;
	
	

	public SokobanLevelDisplayer(){
		wallFilename= new SimpleStringProperty();
		charFilename = new SimpleStringProperty();
		cCol=5;
		cRow =5;
	}
	
	public void setCharacterPosition(int row,int col){
		cRow=row;
		cCol=col;
		redraw(); 
		}
	
	public int getcCol() {
		return cCol;
	}


	public int getcRow() {
		return cRow;
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
	
	public void setLevelData(int[][] levelData) {
		LevelData = levelData;
		redraw();
	}	
	
	public void redraw(){
	
		if(LevelData!=null){
			double W =getWidth();
			double H=getHeight();
			double w =W /LevelData[0].length;
			double h=H/LevelData.length;
			
			GraphicsContext	gc = getGraphicsContext2D();
			
			Image CharacterSoko=null;
			try{
			CharacterSoko = new Image(new FileInputStream(charFilename.get()));
			}
			catch (FileNotFoundException e) {
			e.printStackTrace();
			}
			
			Image Wall=null;
			try{
			Wall = new Image(new FileInputStream(wallFilename.get()));
			}
			catch (FileNotFoundException e) {
			e.printStackTrace();
			}
			
			
			
			gc.clearRect(0,0, W, H);
			
			for(int i=0;i<LevelData.length;i++)
				for(int j=0;j<LevelData[i].length;j++){
					
					if(LevelData[i][j]==1){
				
					gc.drawImage(Wall,j*w, i*h, w, h);
					}

		    }
			
			gc.drawImage(CharacterSoko, cCol*w, cRow*h, w, h);
	
	}
   }
	
}