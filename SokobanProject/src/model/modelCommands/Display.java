package model.modelCommands;

import model.Data.Level;

public class Display implements Command {

	private Level DisplayLevel;
	LevelDisplaer leveltoDisplay;
	
	public Display(Level DisplayLevel){
		
	this.DisplayLevel=DisplayLevel;
		
	}
	
	@Override
	public Level execute() {
		leveltoDisplay = new LevelDisplaer(DisplayLevel);
		leveltoDisplay.displaylevel();
		
		return DisplayLevel;
		
		
	}

}