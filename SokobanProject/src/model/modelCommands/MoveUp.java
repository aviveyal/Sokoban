package model.modelCommands;

import model.Data.Level;
import model.Data.MoveSoko;



public class MoveUp  extends Move{

	public MoveUp(Level playlevel) {
		super(playlevel);
	}

	@Override
	public Level execute() {
		
		MoveSoko Moving= new MoveSoko(playlevel);
		Moving.MovePlayer(playlevel,'w');
		
		return playlevel;
		}

}
