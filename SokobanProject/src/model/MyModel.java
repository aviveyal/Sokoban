package model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;


import model.Data.Level;
import model.modelCommands.Exit;
import model.modelCommands.Load;
import model.modelCommands.Move;
import model.modelCommands.MoveDown;
import model.modelCommands.MoveLeft;
import model.modelCommands.MoveRight;
import model.modelCommands.MoveUp;
import model.modelCommands.Save;

public class MyModel extends Observable implements Model {

	
	private Level level;
	
	
	@Override
	public Level getCurrentLevel() {
		
		return level;
	}

	@Override
	public void mMoveCommand(String direction) {
		switch(direction)	
		{
		case "up":
			Move mu = new MoveUp(this.level);
			this.SetLevel(mu.execute());
			break;
		case "down":
			Move md = new MoveDown(this.level);
			this.SetLevel(md.execute());
			break;
		case "left":
			Move ml = new MoveLeft(this.level);
			this.SetLevel(ml.execute());
			break;
		case "right":
			Move mr = new MoveRight(this.level);
			this.SetLevel(mr.execute());
			break;
		default:
			break;
		}
		
		this.setChanged();
		List<String> params=new LinkedList<String>();
		params.add("display");
		this.notifyObservers(params);		
	}

	@Override
	public void mLoadCommand(String filepath) throws IOException {
		
		
			Load L = new Load(filepath);
			this.SetLevel(L.execute());
			this.setChanged();
			List<String> params=new LinkedList<String>();
			params.add("display");
			this.notifyObservers(params);
		
	}

	@Override
	public void mSaveCommand(String filepath) throws IOException {
		
		Save s = new Save(filepath,level);
		this.SetLevel(s.execute());		
		this.setChanged();

	}

	@Override
	public void SetLevel(Level level) {
		this.level=level;
	}

	@Override
	public void mExit() {
		Exit x = new Exit();
		x.execute();
		this.setChanged();
		
	}

	
}
