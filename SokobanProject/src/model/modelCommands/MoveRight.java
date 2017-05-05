package model.modelCommands;

import model.Data.Level;



public class MoveRight  extends Move{

	MoveSoko Moving;
	public MoveRight(Level playlevel) {
		super(playlevel);
	}

	@Override
	public Level execute() {
		
		Moving= new MoveSoko(playlevel);
		Moving.MovePlayer(playlevel,'d');
		
		return playlevel;
	}

}