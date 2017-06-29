package model.modelCommands;


import model.Data.Level;
//import model.Data.Level;
/**
 * 
 * @author Aviv Eyal
 *	closing the game and the program
 */
public class Exit implements Command {

	private Level level;
	
	public Level execute() {
		System.exit(1);
		return level;
	}

}
