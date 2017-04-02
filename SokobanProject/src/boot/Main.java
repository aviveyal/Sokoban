package boot;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

import java.io.File;

import Database.SokobanDBManager;
import Database.Users;
import controller.SokobanController;
import controller.server.MyClientHandler;
import controller.server.server;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.MyModel;
import view.CLI;
import view.MainWindowController;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;

import javafx.fxml.FXMLLoader;

public class Main extends Application {

	private static final int INDEFINITE = 0;

	
	
/*	

		boolean startgui = true;

		if (args.length != 0) {

			if (args[0].equals("-server")) {

				MyModel model = new MyModel();
				CLI view = new CLI();
				MyClientHandler clientHandler = new MyClientHandler(System.in, System.out);
				SokobanController controller = new SokobanController(view, model);
				model.addObserver(controller);
				view.addObserver(controller);
				try {

					server server = new server(Integer.parseInt(args[1]), clientHandler);
					controller.setServer(server);
					clientHandler.addObserver(controller);
					server.start();
					startgui = false;
				} catch (Exception e) {
					System.out.println("cant build a server");
				}

			}

		}
		if (startgui)
			launch(args);

	}
*/
	@Override
	public void start(Stage primaryStage) {
/*
		

		try {
			// background music
			
			AudioClip mediaPlayer = new AudioClip(new File("./resources/gamemusic.mp3").toURI().toString());
	        mediaPlayer.play();
	        mediaPlayer.setCycleCount(INDEFINITE);
			  
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane) loader.load();
			root.setStyle("-fx-border-width: 3; -fx-border-color: burlywood;");
			
			MainWindowController view = loader.getController();
			MyModel model = new MyModel();
			SokobanController controller = new SokobanController(view, model);

			model.addObserver(controller);
			view.addObserver(controller);

			Scene scene = new Scene(root, 600, 500);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			  
			primaryStage.setTitle("Sokoban - Aviv Eyal");
			primaryStage.setScene(scene);
			primaryStage.show();

			// close window
			primaryStage.setOnCloseRequest(e -> {

				model.mExit();
				Platform.exit();
				System.exit(0);
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

*/
	}
	
}
