package model;

import java.io.IOException;


import model.Data.Level;

public interface Model {


	public Level getCurrentLevel();
	public void SetLevel(Level level);
	public void mMoveCommand(String direction);
	public void mLoadCommand(String filepath) throws IOException;
	public void mSaveCommand(String filepath) throws IOException;
	public void mExit();
	
}
