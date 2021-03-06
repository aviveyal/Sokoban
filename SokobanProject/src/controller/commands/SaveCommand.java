package controller.commands;

import java.io.IOException;

import model.Model;

/**
 * 
 * @author Aviv Eyal update model the save current level
 */
public class SaveCommand extends Command {

	public SaveCommand(Model model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		try {
			String filename = params.get(0);
			model.mSaveCommand(filename);
		} catch (Exception e) {

		}

	}

}
