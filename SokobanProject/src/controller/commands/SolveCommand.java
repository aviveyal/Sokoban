package controller.commands;

import java.io.IOException;

import model.Model;

public class SolveCommand extends Command {

	public SolveCommand(Model model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		try {
			String levelname = params.get(0);
			model.mSaveCommand(levelname);
		} catch (Exception e) {

		}
	}

}
