package view;


import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

	

import view.SokobanLevelDisplayer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Data.Level;

public class MainWindowController extends Observable implements Initializable,View {
	
	
	Level level;
	
	
	
	@FXML
	SokobanLevelDisplayer SokobanLevelDisplayer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		SokobanLevelDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->SokobanLevelDisplayer.requestFocus());
		SokobanLevelDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
			
			List<String> params = new LinkedList<String>();
			
			if(event.getCode()==KeyCode.UP)
			{
				params.add("move");
				params.add("up");
			}	
			else if(event.getCode()==KeyCode.DOWN)
			{
				
				params.add("move");
				params.add("down");
				
			}		
			else if(event.getCode()==KeyCode.LEFT)
			{
				
				params.add("move");
				params.add("left");
				
			}	
			else if(event.getCode()==KeyCode.RIGHT)
			{
				
				params.add("move");
				params.add("right");
			}				
			
			setChanged();
			notifyObservers(params);
			
			}
			
			
		});
	}
	

	
	public void OpenFile (){
		List<String> params = new LinkedList<String>();
		FileChooser fc= new FileChooser();
		fc.setTitle("open Level file");
		fc.setInitialDirectory(new File("./levels"));
		//Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Level files","*.txt","*.obj","*.xml");
		fc.getExtensionFilters().add(extFilter);
		File chosen = fc.showOpenDialog(null);
		
		if(chosen!=null)
		{
			params.add("load");
			params.add("levels/"+chosen.getName());
			setChanged();
			notifyObservers(params);
			
		}
		
		
	}
	public void SaveFile (){
		List<String> params = new LinkedList<String>();
		FileChooser fc= new FileChooser();
		fc.setTitle("save Level file");
		fc.setInitialDirectory(new File("./levels"));
		
		//Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Level files","*.txt","*.obj","*.xml");
        
        fc.getExtensionFilters().add(extFilter);
		File chosen = fc.showSaveDialog(null);
		
		if(chosen!=null)
		{
			params.add("save");
			params.add("levels/"+chosen.getName());
			setChanged();
			notifyObservers(params);
			
		}
		
	}
	
	public void Exit (){
		List<String> params = new LinkedList<String>();
		params.add("exit");
		setChanged();
		notifyObservers(params);
	}

	@Override
	public void mDisplayCommand(Level level) {
		
		SokobanLevelDisplayer.setLevelData(level); //sends current level data
		SokobanLevelDisplayer.redraw();
		
		}



	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}		
	}

	

