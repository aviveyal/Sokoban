package model.modelCommands;



import model.Data.Level;

public class MoveDown  extends Move{

	MoveSoko Moving;
	public MoveDown(Level playlevel) {
		super(playlevel);
	}

	@Override
	public Level execute() {
		
		Moving= new MoveSoko(playlevel);
		Moving.MovePlayer(playlevel,'s');
		
		return playlevel;
	}

}