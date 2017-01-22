package view;
	


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

import controller.SokobanController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	javafx.scene.media.MediaPlayer MediaPlayer;
	@Override
	public void start(Stage primaryStage) {
		try {
			//background music
			Media musicFile =new Media(new File("./resources/gamemusic.mp3").toURI().toString());
			MediaPlayer = new MediaPlayer(musicFile);
			MediaPlayer.setAutoPlay(true);
			
			
			FXMLLoader loader=new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane)loader.load();
			
			MainWindowController view=loader.getController();
			MyModel model = new MyModel();
			SokobanController controller = new SokobanController(view, model);
			
			model.addObserver(controller);
			view.addObserver(controller);
			
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
						
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
			launch(args);	
			
		
	}
}
