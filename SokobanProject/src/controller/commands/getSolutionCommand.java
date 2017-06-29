package controller.commands;

import java.io.IOException;

import model.Model;
import model.Data.Level;
import view.View;

/**
 * 
 * @author Aviv Eyal the model return the solution for the view layer
 * 
 */

public class getSolutionCommand extends Command {

	public getSolutionCommand(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void execute() throws IOException {
		Level level = model.getCurrentLevel();

		view.getSolution(params.get(0));

	}
}