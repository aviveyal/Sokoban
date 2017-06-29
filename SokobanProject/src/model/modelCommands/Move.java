package model.modelCommands;

import model.Data.Level;

/**
 * 
 * @author Aviv Eyal part of command pattern father class- all subclass holding
 *         specific direction and extends this
 */

abstract public class Move implements Command {

	public Level playlevel;
		
	public Move(Level playlevel){
			this.playlevel=playlevel;
	}

	@Override
	abstract public Level execute();
				
}		
