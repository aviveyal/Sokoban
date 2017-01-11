package Commands;

import levels.Level;
import levels.MoveSoko;

public class MoveDown  extends Move{

	public MoveDown(Level playlevel) {
		super(playlevel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Level execute() {
		
		MoveSoko Moving= new MoveSoko(playlevel);
		Moving.MovePlayer(playlevel,'s');
		
		return playlevel;
	}

}