package controller.commands;

import java.io.IOException;

import model.Model;
import model.Data.Level;
import view.View;

public class DisplayCommand extends Command{

	public DisplayCommand(Model model,View view) {
		this.model = model;
		this.view=view;
	}
	@Override
	public void execute() {
		Level level=model.getCurrentLevel();
			
		view.mDisplayCommand(level);
		
	
	}

}
