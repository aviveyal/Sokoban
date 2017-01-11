package Commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import levels.Level;
import levels.LevelLoader;
import levels.MyObjectLevelLoader;
import levels.MyTextLevelLoader;
import levels.MyXMLLevelLoader;

public class Load implements Command {

	private String filename;
	
	public Load(String filename){
		
		this.filename=filename;
		
	}	
	
	@Override
	public Level execute() throws IOException {
		HashMap<String,LevelLoader> hmap = new HashMap<String,LevelLoader>();
		
		/*Adding elements to HashMap*/
	    hmap.put("txt",new MyTextLevelLoader());
	    hmap.put("xml",new MyXMLLevelLoader());
	    hmap.put("obj",new MyObjectLevelLoader());
		
	    /*Create new object depends on file type*/
		String endfile = filename.substring(filename.length()-3);
		LevelLoader LevelLOAD = hmap.get(endfile);
		
		
		File file = new File(filename);
		
		if (file.exists())//check if file exists - if not, close the program 
		{
			 FileInputStream In = new FileInputStream(file);
			 LevelLOAD.loadlevel(In);
			 return LevelLoader.levelloader;	
		}
		else
		{
			System.out.println("file not exists!Please try again");
			System.exit(1);
			return LevelLoader.levelloader;
			
	    }	 
		    		
}
}
