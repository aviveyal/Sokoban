package boot;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.persistence.criteria.Root;

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
import view.PopUpController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

/**
 * 
 * @author Aviv Eyal Main class of sokoban client must run with server open open
 *         a GUI and set model, view, controller
 */

public class Main extends Application {

	private static final int INDEFINITE = 0;

	private Stage primaryStage;
	Scene scene1, scene2;

	public static void main(String[] args) {

		boolean startgui = true;

		if (args.length != 0) {

			if (args[0].equals("-server")) {

				MyModel model = new MyModel();
				CLI view = new CLI();
				MyClientHandler clientHandler = new MyClientHandler(System.in, System.out);

			/*	Socket socket = null;
				try {
					socket = new Socket("127.0.0.1", 5555);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("connected to server");*/

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
			Application.launch(Main.class, new String[0]);

	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;

		try {
			// background music

			AudioClip mediaPlayer = new AudioClip(new File("./resources/gamemusic.mp3").toURI().toString());
			mediaPlayer.play();
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane) loader.load();
			root.setStyle("-fx-border-width: 3; -fx-border-color: burlywood;");

		/*	Socket socket = new Socket("127.0.0.1", 5555);
			System.out.println("connected to server");
		 */
			MainWindowController view = loader.getController();
			MyModel model = new MyModel();
			SokobanController controller = new SokobanController(view, model);

			model.addObserver(controller);
			view.addObserver(controller);

			scene1 = new Scene(root, 600, 520);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setTitle("Sokoban - Aviv Eyal");
			primaryStage.setScene(scene1);

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

}
