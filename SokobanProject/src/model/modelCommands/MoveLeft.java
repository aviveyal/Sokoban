package model.modelCommands;

import model.Data.Level;

public class MoveLeft extends Move{

	MoveSoko Moving;
	public MoveLeft(Level playlevel) {
		super(playlevel);
	}

	@Override
	public Level execute() {
		
		Moving= new MoveSoko(playlevel);
		Moving.MovePlayer(playlevel,'a');
		
		return playlevel;
	}

}