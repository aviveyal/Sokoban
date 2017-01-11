package Commands;

import levels.Level;
import levels.MoveSoko;

public class MoveRight  extends Move{

	public MoveRight(Level playlevel) {
		super(playlevel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Level execute() {
		
		MoveSoko Moving= new MoveSoko(playlevel);
		Moving.MovePlayer(playlevel,'d');
		
		return playlevel;
	}

}