package model.modelCommands;




import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import model.Data.Level;
import model.Data.LevelSaver;
import model.Data.MyObjectLevelSaver;
import model.Data.MyTextLevelSaver;
import model.Data.MyXMLLevelSaver;



public class Save implements Command {

	private String filename;
	private Level level;
	
	public Save(String filename,Level level) {
		
	this.filename=filename;
	this.level=level;	
	}
	
	
	
	@Override
	public Level execute() throws IOException {
		
		HashMap<String,LevelSaver> hmap = new HashMap<String,LevelSaver>();
		
		/*Adding elements to HashMap*/
	    hmap.put("txt",new MyTextLevelSaver());
	    hmap.put("xml",new MyXMLLevelSaver());
	    hmap.put("obj",new MyObjectLevelSaver());
	    
	    /*Create new object depends on file type*/
		String endfile = filename.substring(filename.length()-3);
		LevelSaver LevelSAVE = hmap.get(endfile);
		FileOutputStream out = new FileOutputStream(filename,false);
		
		LevelSAVE.savelevel(out,level);
		
	    return level;
		
	    
	}

}