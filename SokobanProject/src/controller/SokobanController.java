package controller;

import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import controller.commands.Command;
import controller.commands.DisplayCommand;
import controller.commands.ExitCommand;
import controller.commands.LoadCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveCommand;
import controller.commands.SolveCommand;
import controller.commands.getSolutionCommand;
import controller.server.server;
import javafx.scene.Scene;
import model.Model;
import view.View;

/**
 * 
 * @author Aviv Eyal The sokoban controller observer and get notification from
 *         view and model (make connection between those layers) all possible
 *         command of the game insert here
 */
public class SokobanController implements Observer, ControllerInterface {

	private View v;
	private Model m;
	private Controller controller;
	private Map<String, Command> commands;
	private server server;
	private Socket socket = null;

	public View getV() {
		return v;
	}

	public void setV(View v) {
		this.v = v;
	}

	public Model getM() {
		return m;
	}

	public void setM(Model m) {
		this.m = m;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public SokobanController(View v, Model m) {
		this.v = v;
		this.m = m;
		
		initCommands();
		controller = new Controller();
		controller.start();

	}

	public void initCommands() {

		commands = new HashMap<String, Command>();
		commands.put("move", new MoveCommand(m));
		commands.put("save", new SaveCommand(m));
		commands.put("load", new LoadCommand(m));
		commands.put("display", new DisplayCommand(m, v));
		commands.put("exit", new ExitCommand(m, controller));
		commands.put("solve", new SolveCommand(m));
		commands.put("getSolution", new getSolutionCommand(m, v));

	}

	@Override
	public void update(Observable o, Object arg) {
		LinkedList<String> params = (LinkedList<String>) arg;
		String commandKey = params.removeFirst();
		Command c = commands.get(commandKey);
		if (c == null) {
			System.out.println("Command " + commandKey + " not found");
			return;
		}
		if (commandKey.equals("exit")) // thread close
		{
			controller.stop();
			if (server != null)
				Stop();
		}

		c.setParams(params);
		controller.insertCommand(c);
	}

	public void setServer(server server) {
		this.server = server;

	}

	@Override
	public void Stop() {
		server.stop();

	}

}
