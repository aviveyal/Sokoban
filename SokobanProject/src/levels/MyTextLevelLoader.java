package levels;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;



public class MyTextLevelLoader implements LevelLoader {

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

		
	
	@Override
	public Level loadlevel(FileInputStream InputStream) throws IOException 
	{
		BufferedInputStream readinput = new BufferedInputStream(InputStream);
		
			
		
		int x = 0 ;
		int y = 0 ;
		int read;
		
		while((read =readinput.read()) != -1)
			{
			
					if((char)read==startwall.getWallsymbol())
					{
						wall.add(new Wall(x,y));
					}
					else if ((char)read==startbox.getBoxessymbol())
					{
						box.add(new Box(x,y));
					}
					else if((char)read==starttarget.getTargetsymbol())
					{
						target.add(new Target(x,y));
					}
					
					else if ((char)read==startsoko.getSokosymbol())
					{
						Soko.add(new SokoChar(x,y));
					}
					else if ((char)read==startBOT.boxesontargetsymbol())
					{
						BOT.add(new BoxOntarget(x,y));
					}
					
					else if((char)read=='\n')
					{
						x++;
						y=-1; //it gets +1 later
					}
										
					y++;
				
			}
		
			InputStream.close();
			readinput.close();
			
			
			//set the object into the level
			levelloader.setWalls(wall);
			levelloader.setBoxes(box);
			levelloader.setTargets(target);
			levelloader.setSokoCharas(Soko);
			levelloader.setBoxOnTareget(BOT);
			
			
			return levelloader;
			
	}
	
	
	
	
}
