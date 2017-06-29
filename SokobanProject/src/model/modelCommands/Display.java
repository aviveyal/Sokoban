package model.modelCommands;

import model.Data.Level;

/**
 * 
 * @author Aviv Eyal part of command pattern in the execute call a function that
 *         can make the action
 */
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