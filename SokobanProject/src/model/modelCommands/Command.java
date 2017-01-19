package model.modelCommands;

import java.io.IOException;

import model.Data.Level;



public interface Command {

	public Level execute() throws IOException;
	
}