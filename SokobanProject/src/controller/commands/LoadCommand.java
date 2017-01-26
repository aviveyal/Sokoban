package controller.commands;

import java.io.IOException;

import model.Model;

public class LoadCommand extends Command {

		public LoadCommand(Model model) {
		this.model = model;
	}
	@Override
	public void execute() throws IOException {
		try{
			
		String filename=params.get(0);
		model.mLoadCommand(filename);
		}
		catch (Exception e) {
			
		}
		
		

	}

}
