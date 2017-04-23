package model.Data;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;



public class MyObjectLevelLoader implements LevelLoader {

	
	private char startwall = Wall.wallsymbol;
	private char startsoko = SokoChar.sokosymbol;
	private char starttarget  = Target.targetsymbol;
	private char startbox = Box.boxessymbol;
	private char startBOT  = BoxOntarget.boxesontargetsymbol;

		
	
	@Override
	public Level loadlevel(FileInputStream InputStream) throws IOException 
	{
		
		BufferedInputStream readinput = new BufferedInputStream(InputStream);
		
		int x = 0 ;
		int y = 0 ;
		int read;
		
		while((read =readinput.read()) != -1)
			{
			
			
					if((char)read==startwall)
					{
						levelloader.getWalls().add(new Wall(x,y));
					}
					else if ((char)read==startbox)
					{
						levelloader.getBoxes().add(new Box(x,y));
					}
					else if((char)read==starttarget)
					{
						levelloader.getTargets().add(new Target(x,y));
					}
					
					else if ((char)read==startsoko)
					{
						levelloader.getSokoCharas().add(new SokoChar(x,y));
					}
					else if ((char)read==startBOT)
					{
						levelloader.getBoxOnTareget().add(new BoxOntarget(x,y));
					}
					
					else if((char)read=='\n')
					{
						x++;
						y=-1; //it gets +1 later
					}
										
					y++;
				
			}
		
			InputStream.close();
			
					
			return levelloader;
			
	}
	
	
	
	
}