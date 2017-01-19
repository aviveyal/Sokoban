package model.modelCommands;


import model.Data.Level;
//import model.Data.Level;

public class Exit implements Command {

	private Level level;
	
	public Level execute() {
		System.exit(1);
		return level;
	}

}
