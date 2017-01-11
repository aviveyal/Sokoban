package Commands;

import levels.Level;
import levels.MoveSoko;

public class MoveLeft  extends Move{

	public MoveLeft(Level playlevel) {
		super(playlevel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Level execute() {
		
		MoveSoko Moving= new MoveSoko(playlevel);
		Moving.MovePlayer(playlevel,'a');
		
		return playlevel;
	}

}