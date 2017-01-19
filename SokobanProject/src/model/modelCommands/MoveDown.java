package model.modelCommands;



import model.Data.Level;
import model.Data.MoveSoko;

public class MoveDown  extends Move{

	public MoveDown(Level playlevel) {
		super(playlevel);
	}

	@Override
	public Level execute() {
		
		MoveSoko Moving= new MoveSoko(playlevel);
		Moving.MovePlayer(playlevel,'s');
		
		return playlevel;
	}

}