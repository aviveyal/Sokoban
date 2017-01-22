package view;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

	

import view.SokobanLevelDisplayer;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class MainWindowController implements Initializable {
	
	int [][]LevelData={
			{1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,0,0,0,0,0,0,0,1},
			{1,1,1,1,0,0,0,0,0,0,0,1},
			{1,1,1,1,0,0,0,0,0,0,0,1},
			{1,1,1,1,0,0,0,0,0,0,0,1},
			{1,1,1,1,0,0,0,0,0,0,0,1},
			{1,1,1,1,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1},
	};
		
	
	
	@FXML
	SokobanLevelDisplayer SokobanLevelDisplayer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		SokobanLevelDisplayer.setLevelData(LevelData);
		SokobanLevelDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->SokobanLevelDisplayer.requestFocus());
		
		SokobanLevelDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
			int r=SokobanLevelDisplayer.getcRow();
			int c=SokobanLevelDisplayer.getcCol();
			
			if(event.getCode()==KeyCode.UP)
					SokobanLevelDisplayer.setCharacterPosition(r-1,c);
			else if(event.getCode()==KeyCode.DOWN)
				SokobanLevelDisplayer.setCharacterPosition(r+1,c);
			else if(event.getCode()==KeyCode.LEFT)
				SokobanLevelDisplayer.setCharacterPosition(r,c-1);
			else if(event.getCode()==KeyCode.RIGHT)
				SokobanLevelDisplayer.setCharacterPosition(r,c+1);
			}
				
			
			
		});
	}
	

	public void start(){
		System.out.println("Start");
	}
	public void OpenFile (){
		FileChooser fc= new FileChooser();
		fc.setTitle("open Level file");
		fc.setInitialDirectory(new File("./resources"));
		//extension filter
		File chosen = fc.showOpenDialog(null);
		if(chosen!=null)
		{
			System.out.println(chosen.getName());
		}
	}

	
}
