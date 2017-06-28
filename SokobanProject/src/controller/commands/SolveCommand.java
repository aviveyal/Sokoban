package controller.commands;

import java.io.IOException;
import java.net.Socket;

import model.Model;

public class SolveCommand extends Command {

	private Socket socket;
	public SolveCommand(Model model,Socket socket) {
		this.model = model;
		this.socket=socket;
	}

	@Override
	public void execute() throws IOException {
		try {
			String levelname = params.get(0);
			model.SendToServer(levelname,socket);
		} catch (Exception e) {

		}
	}

}
