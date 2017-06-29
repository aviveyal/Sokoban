package controller.commands;

import java.io.IOException;

import model.Model;
import model.Data.Level;
import view.View;

/**
 * 
 * @author Aviv Eyal update view with new display
 */
public class DisplayCommand extends Command{

	public DisplayCommand(Model model,View view) {
		this.model = model;
		this.view=view;
	}
	@Override
	public void execute() throws IOException {
		Level level=model.getCurrentLevel();
			
		view.mDisplayCommand(level);
		
	
	}

}
