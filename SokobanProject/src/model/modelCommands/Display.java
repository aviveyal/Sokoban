package model.modelCommands;

import model.Data.Level;

public class Display implements Command {

	private Level DisplayLevel;
	
	public Display(Level DisplayLevel){
		
	this.DisplayLevel=DisplayLevel;
		
	}
	
	@Override
	public Level execute() {
		LevelDisplaer leveltoDisplay = new LevelDisplaer(DisplayLevel);
		leveltoDisplay.displaylevel();
		
		return DisplayLevel;
		
		
	}

}