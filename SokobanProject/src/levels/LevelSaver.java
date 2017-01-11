package levels;

import java.io.FileOutputStream;
import java.io.IOException;

public interface LevelSaver {
	
	public void savelevel(FileOutputStream outputStream,Level levelSaver) throws IOException;
	//Level levelSaver= new Level();
}
