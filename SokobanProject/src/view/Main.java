package view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import controller.SokobanController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	javafx.scene.media.MediaPlayer MediaPlayer;

	@Override
	public void start(Stage primaryStage) {
		try {
			// background music
			Media musicFile = new Media(new File("./resources/gamemusic.mp3").toURI().toString());
			MediaPlayer = new MediaPlayer(musicFile);
			MediaPlayer.setAutoPlay(true);
			MediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);// loop music

			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane) loader.load();
			MainWindowController view = loader.getController();
			MyModel model = new MyModel();
			SokobanController controller = new SokobanController(view, model);

			model.addObserver(controller);
			view.addObserver(controller);

			Scene scene = new Scene(root, 600, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

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
	}

	public static void main(String[] args) {
		launch(args);

	}
}
