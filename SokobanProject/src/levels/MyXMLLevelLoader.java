package levels;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;




public class MyXMLLevelLoader implements LevelLoader {
	
	private ArrayList<Wall> wall = new ArrayList<Wall>();
	private ArrayList<Box> box =new ArrayList<Box>();
	private ArrayList<Target> target=new ArrayList<Target>();
	private ArrayList<SokoChar> Soko=new ArrayList<SokoChar>();
	private ArrayList<BoxOntarget> BOT = new ArrayList<BoxOntarget>();
	
	//creating new objects- just for use the symbol - not in the ArrayList!
	private SokoChar startsoko = new SokoChar(0,0); 
	private Wall startwall = new Wall(0,0); 
	private Target starttarget = new Target(0,0);
	private Box startbox = new Box(0,0);
	private BoxOntarget startBOT = new  BoxOntarget(0, 0);

	
	@SuppressWarnings("finally")
	@Override
	public Level loadlevel(FileInputStream InputStream) throws IOException 
	{
			
		int x = 0 ;
		int y = 0 ;
		
		
		
	XMLDecoder e = new XMLDecoder(new BufferedInputStream(InputStream)); //using XML Decoder
	
				
	try {
		
	
		Object read2 = e.readObject();//read from an XML
		
		String read= read2.toString();
		
				
			
		
		while(true)
		   {
				
					if(read.charAt(0)==startwall.getWallsymbol())
					{
						wall.add(new Wall(x,y));
												
					}
					else if (read.charAt(0)==startbox.getBoxessymbol())
					{
						box.add(new Box(x,y));
											}
					else if(read.charAt(0)==starttarget.getTargetsymbol())
					{
						target.add(new Target(x,y));
											}
					
					else if (read.charAt(0)==startsoko.getSokosymbol())
					{
						Soko.add(new SokoChar(x,y));
					}
					else if (read.charAt(0)==startBOT.boxesontargetsymbol())
					{
						BOT.add(new BoxOntarget(x,y));
					}
					else if(read.equals("\r\n")) // condition for end of line ???
					{
						
						x++;
						y=-1; //it gets +1 later
					}
					
					y++;
					
					read2 = e.readObject();
					read= read2.toString();
					
		
		   }
	}
	
		finally {
		
		e.close();	
		
		//set the object into the level
		levelloader.setWalls(wall);
		levelloader.setBoxes(box);
		levelloader.setTargets(target);
		levelloader.setSokoCharas(Soko);
		levelloader.setBoxOnTareget(BOT);
		
		
		return levelloader;
				
		   }
		
	   			
	
	
			
			
			
	}
	
	
	
	
}
	
