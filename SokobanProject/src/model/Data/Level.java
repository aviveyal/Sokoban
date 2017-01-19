package model.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {
		
	
	//objects 
		
	private static final long serialVersionUID = 1L;
	private ArrayList<Wall> Walls;
	private ArrayList<Box> Boxes;
	private ArrayList<Target> Targets;
	private ArrayList<SokoChar> SokoCharas;
	private ArrayList<BoxOntarget> BoxOnTareget;
	

	//constructor
	public Level(){
	}
		

	//Getters and Setters
		
	public ArrayList<Wall> getWalls() {
		return Walls;
	}
	public void setWalls(ArrayList<Wall> walls) {
		Walls = walls;
	}
	public ArrayList<Box> getBoxes() {
		return Boxes;
	}
	public void setBoxes(ArrayList<Box> boxes) {
		Boxes = boxes;
	}
	public ArrayList<Target> getTargets() {
		return Targets;
	}
	public void setTargets(ArrayList<Target> targets) {
		this.Targets = targets;
	}
	public ArrayList<SokoChar> getSokoCharas() {
		return SokoCharas;
	}
	public void setSokoCharas(ArrayList<SokoChar> sokoCharas) {
		SokoCharas = sokoCharas;
	}

	
	public ArrayList<BoxOntarget> getBoxOnTareget() {
		return BoxOnTareget;
	}


	public void setBoxOnTareget(ArrayList<BoxOntarget> boxOnTareget) {
		BoxOnTareget = boxOnTareget;
	}

	//functions
	
	public int maxcolumnsize()//number of columns
	{
		int y=0;
		
		for(int i=0 ; i<Walls.size();i++)
			if(y<Walls.get(i).getY())
				y=Walls.get(i).getY();
		
		for(int i=0 ; i<Boxes.size();i++)
			if(y<Boxes.get(i).getY())
				y=Boxes.get(i).getY();
		
		for(int i=0 ; i<Targets.size();i++)
			if(y<Targets.get(i).getY())
				y=Targets.get(i).getY();
		
		for(int i=0 ; i<SokoCharas.size();i++)
			if(y<SokoCharas.get(i).getY())
				y=SokoCharas.get(i).getY();
								
		return y;
		
	}
	
	public int maxrowsize()//number of rows
	{
		int x=0;
		
		for(int i=0 ; i<Walls.size();i++)
			if(x<Walls.get(i).getX())
				x=Walls.get(i).getX();
		
		for(int i=0 ; i<Boxes.size();i++)
			if(x<Boxes.get(i).getX())
				x=Boxes.get(i).getX();
		
		for(int i=0 ; i<Targets.size();i++)
			if(x<Targets.get(i).getX())
				x=Targets.get(i).getX();
		
		for(int i=0 ; i<SokoCharas.size();i++)
			if(x<SokoCharas.get(i).getX())
				x=SokoCharas.get(i).getX();
				
				
		return x;
		
	}
	
	public String[][] makestring()
	{
		int wallsnumber=0;
		int targetsnumber=0;
		int boxesnumber=0;
		int sokonumber=0;
		
		int maxcolumnsize =this.maxcolumnsize();
		int maxrowsize=this.maxrowsize();
		
		Wall firstwall =this.Walls.get(wallsnumber); 
		Target firsttarget = this.Targets.get(targetsnumber);
		Box firstbox =this.Boxes.get(boxesnumber);
		SokoChar firstsoko = this.SokoCharas.get(sokonumber);
		
		this.BoxOnTareget.add(new BoxOntarget(-1,-1 ));//in case there are no boxes on target
		BoxOntarget firstBOT =this.BoxOnTareget.get(0);
		
		boolean addedtostring= false;
		
		
		String [][]Level = new String [maxrowsize+1][maxcolumnsize+1];
		
		
		for(int x=0;x<=maxrowsize;x++)
		{
			for(int y=0 ;y<=maxcolumnsize;y++)
			{
				addedtostring=false;
		
				
				if(firstwall.getX()==x && firstwall.getY()==y )
				{
					Level[x][y]=Character.toString(firstwall.getWallsymbol());
					wallsnumber++;
					if(wallsnumber<this.Walls.size())
						firstwall =this.Walls.get(wallsnumber); 
				}
				else if (firsttarget.getX()==x && firsttarget.getY()==y )
				{
					for(int i=0; i<this.getBoxOnTareget().size(); i++)//check all boxes on target array
					{
						
							if(this.getBoxOnTareget().get(i).getX()==x && this.getBoxOnTareget().get(i).getY()==y)
							{
								Level[x][y]=Character.toString(firstBOT.boxesontargetsymbol());
																				
								addedtostring = true;
							}
					}
					
					if(!addedtostring)
					{
									
					Level[x][y]=Character.toString(firsttarget.getTargetsymbol());;
					targetsnumber++;
					if(targetsnumber<this.Targets.size())
						firsttarget =this.Targets.get(targetsnumber);
					}
					
				}
			
				
				else if (firstsoko.getX()==x && firstsoko.getY()==y)
				{
					Level[x][y]=Character.toString(firstsoko.getSokosymbol());
					sokonumber++;
					if(sokonumber<this.SokoCharas.size())
						firstsoko =this.SokoCharas.get(sokonumber);
				}
				
						
				else //boxes can change order in the arraylist so we need check all boxes places 
				{
					for(int i=0;i<this.getBoxes().size();i++)//check all boxes
					{
						
							if ((this.getBoxes().get(i).getX()== x)&&(this.getBoxes().get(i).getY()==y))
							{
								Level[x][y]=Character.toString(firstbox.getBoxessymbol());
							}
						
					}
				}
				
				
				if(Level[x][y]==null) //last option
				{
					Level[x][y]=" ";
				}
				
				
			
			}
		}
	
				
		return Level;
	}
	
}
