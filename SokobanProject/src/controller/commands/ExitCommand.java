package controller.commands;

import java.io.IOException;

import controller.Controller;
import model.Model;

/**
 * 
 * @author Aviv Eyal close the game by updating the model
 */
public class ExitCommand extends Command {

	public ExitCommand(Model model, Controller controller) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {

		model.mExit();
	}
}
