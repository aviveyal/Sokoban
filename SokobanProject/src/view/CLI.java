package view;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import controller.commands.ExitCommand;
import javafx.stage.Stage;
import model.Data.Level;
import model.modelCommands.Display;

public class CLI extends Observable implements View {

	@Override
	public void start() {
		Scanner scanner = new Scanner(System.in);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println(">load filename");
					System.out.println(">display");
					System.out.println(">move {up,dpwn,right,left}");
					System.out.println(">save filename");
					System.out.println(">exit");
					String commandLine = scanner.nextLine();

					String[] arr = commandLine.split(" ");
					List<String> params = new LinkedList<String>();

					for (String s : arr) {
						params.add(s);
					}

					setChanged();
					notifyObservers(params);

					if (commandLine.equals("exit"))

						break;
				}
			}
		});
		thread.start();
	}

	@Override
	public void mDisplayCommand(Level level) {
		Display D = new Display(level);
		D.execute();

		// setChanged();
		// notifyObservers();
	}

}
