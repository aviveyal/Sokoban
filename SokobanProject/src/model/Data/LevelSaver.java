package model.Data;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author Aviv Eyal Save the current state of the level in a new file
 */
public interface LevelSaver {

	public void savelevel(FileOutputStream outputStream, Level levelSaver) throws IOException;

}
