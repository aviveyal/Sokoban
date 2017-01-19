package model.modelCommands;

import model.Data.Level;
import model.Data.MoveSoko;

public class MoveLeft extends Move{

	public MoveLeft(Level playlevel) {
		super(playlevel);
	}

	@Override
	public Level execute() {
		
		MoveSoko Moving= new MoveSoko(playlevel);
		Moving.MovePlayer(playlevel,'a');
		
		return playlevel;
	}

}