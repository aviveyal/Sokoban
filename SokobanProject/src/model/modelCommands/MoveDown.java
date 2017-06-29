package model.modelCommands;

import model.Data.Level;

/**
 * 
 * @author Aviv Eyal part of command pattern in the execute call a function that
 *         can make the action
 */
public class MoveDown extends Move {

	MoveSoko Moving;

	public MoveDown(Level playlevel) {
		super(playlevel);
	}

	@Override
	public Level execute() {

		Moving = new MoveSoko(playlevel);
		Moving.MovePlayer(playlevel, 's');

		return playlevel;
	}

}