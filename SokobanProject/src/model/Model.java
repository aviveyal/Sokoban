package model;

import java.io.IOException;
import java.net.Socket;

import model.Data.Level;
/**
 * 
 * @author Aviv Eyal
 *This is the model layer!
 *all posiible commands include in this intefcae
 */
public interface Model {

	public Level getCurrentLevel();
	public void SetLevel(Level level);
	public void mMoveCommand(String direction);
	public void mLoadCommand(String filepath) throws IOException;
	public void mSaveCommand(String filepath) throws IOException;
	public void SendToServer (String str,Socket socket);
	public void mExit();
	
}
