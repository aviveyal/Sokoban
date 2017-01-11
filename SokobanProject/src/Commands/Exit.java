package Commands;

import levels.Level;

public class Exit implements Command {

	private Level level;
	
	@Override
	public Level execute() {
		System.exit(1);
		return level;		
	}

}
