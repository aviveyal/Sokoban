package model.modelCommands;

import java.io.IOException;

import model.Data.Level;

/**
 * 
 * @author Aviv Eyal
 *part of command pattern
 *execute commands
 */

public interface Command {

	public Level execute() throws IOException;
	
}