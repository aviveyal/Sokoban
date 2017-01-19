package controller.commands;

import java.io.IOException;

import model.Model;

public class ExitCommand extends Command {

	
private Model model;
	
	public ExitCommand(Model model) {
		this.model = model;
	}
	@Override
	public void execute() throws IOException {
		
		model.mExit();
	}
}
