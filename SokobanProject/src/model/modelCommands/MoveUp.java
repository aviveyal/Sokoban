package model.modelCommands;

import model.Data.Level;



public class MoveUp  extends Move{

	MoveSoko Moving;
	public MoveUp(Level playlevel) {
		super(playlevel);
	}

	@Override
	public Level execute() {
		
		Moving= new MoveSoko(playlevel);
		Moving.MovePlayer(playlevel,'w');
		
		return playlevel;
		}

}
