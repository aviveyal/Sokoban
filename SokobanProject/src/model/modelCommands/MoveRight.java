package model.modelCommands;

import model.Data.Level;



public class MoveRight  extends Move{

	public MoveRight(Level playlevel) {
		super(playlevel);
	}

	@Override
	public Level execute() {
		
		MoveSoko Moving= new MoveSoko(playlevel);
		Moving.MovePlayer(playlevel,'d');
		
		return playlevel;
	}

}