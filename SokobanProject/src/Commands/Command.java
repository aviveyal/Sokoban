package Commands;

import java.io.IOException;

import levels.Level;

public interface Command {

	public Level execute() throws IOException;
	
}
