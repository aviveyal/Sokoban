package controller.commands;

import java.io.IOException;

import model.Model;

public class LoadCommand extends Command {

	
private Model model;
	
	public LoadCommand(Model model) {
		this.model = model;
	}
	@Override
	public void execute() throws IOException {
		String filename=params.get(0);
		model.mLoadCommand(filename);

	}

}
