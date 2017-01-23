package model.modelCommands;

import model.Data.Level;

abstract public class Move implements Command {

	public Level playlevel;
		
	public Move(Level playlevel){
			this.playlevel=playlevel;
	}

	@Override
	abstract public Level execute();
				
}		