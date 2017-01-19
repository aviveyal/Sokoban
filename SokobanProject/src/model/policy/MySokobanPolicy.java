package model.policy;

import model.Data.Level;

public class MySokobanPolicy {

		public Level levelPolicy;

		public MySokobanPolicy(Level levelPolicy){
			
		this.levelPolicy= levelPolicy;
			
		}
		
		
		/*
		five different boolean functions :
		1.canmove - check if there is nothing next step the character
		2.BoxOnWay - check if there is a box next step that can be moved (two steps forward is empty)
		3.BoxOnTarget -	check if there is a box next step that can be moved (two steps forward is a target)
		4.VonwayE -	check if there is a box on target in front of the character and it can be moved(two step forward is empty)
		5.VonwayT- check if there is a box on target in front of the character and it can be moved(two step forward is another target)
		
		*/
		
		public boolean BoxOnWay(Level levelPolicy,char direction)//box that can move
		{
			int y =levelPolicy.getSokoCharas().get(0).getY();
			int x=levelPolicy.getSokoCharas().get(0).getX();
			String [][]levelstr=levelPolicy.makestring();
			
			if((char)direction =='w')
			{
			for(int i=0;i<levelPolicy.getBoxes().size();i++)//check all boxes
				{
					if ((levelPolicy.getBoxes().get(i).getX()== x-1)&& 
							(levelPolicy.getBoxes().get(i).getY()==y)) //if there is a box on way
					{
						if(levelstr[x-2][y].equals(" ")) //can move both
							return true;
						else
							return false;
					}
				}
			}
			else if((char)direction =='s')
			{
				for(int i=0;i<levelPolicy.getBoxes().size();i++)//check all boxes
					{
						if ((levelPolicy.getBoxes().get(i).getX()== x+1)&& 
								(levelPolicy.getBoxes().get(i).getY()==y)) //if there is a box on way
						{
							if(levelstr[x+2][y].equals(" ")) //can move both
								return true;
							else
								return false;
						}
					}
				}
			else if((char)direction =='a')
			{
				for(int i=0;i<levelPolicy.getBoxes().size();i++)//check all boxes
					{
						if ((levelPolicy.getBoxes().get(i).getX()==x)&& 
								(levelPolicy.getBoxes().get(i).getY()==y-1)) //if there is a box on way
						{
							if(levelstr[x][y-2].equals(" ")) //can move both
								return true;
							else
								return false;
						}
					}
				}
			else if((char)direction =='d')
			{
				for(int i=0;i<levelPolicy.getBoxes().size();i++)//check all boxes
					{
						if ((levelPolicy.getBoxes().get(i).getX()== x)&& 
								(levelPolicy.getBoxes().get(i).getY()==y+1)) //if there is a box on way
						{
							if(levelstr[x][y+2].equals(" ")) //can move both
								return true;
							else
								return false;
						}
					}
				}
			
			return false;
		}
		
		public boolean BoxOnTarget(Level levelPolicy,char direction)//box that can move
		{
			int y =levelPolicy.getSokoCharas().get(0).getY();
			int x=levelPolicy.getSokoCharas().get(0).getX();
			char targetsymbol = levelPolicy.getTargets().get(0).getTargetsymbol();
			String [][]levelstr=levelPolicy.makestring();
			
			
			if((char)direction =='w')
			{
			for(int i=0;i<levelPolicy.getBoxes().size();i++)//check all boxes
				{
					if ((levelPolicy.getBoxes().get(i).getX()== x-1)&& 
							(levelPolicy.getBoxes().get(i).getY()==y)) //if there is a box on way
					{
						if(levelstr[x-2][y].charAt(0)==targetsymbol) //can move both
							return true;
						else
							return false;
					}
				}
							
			}
			else if((char)direction =='s')
			{
				for(int i=0;i<levelPolicy.getBoxes().size();i++)//check all boxes
					{
						if ((levelPolicy.getBoxes().get(i).getX()== x+1)&& 
								(levelPolicy.getBoxes().get(i).getY()==y)) //if there is a box on way
						{
							if(levelstr[x+2][y].charAt(0)==targetsymbol) //can move both
								return true;
							else
								return false;
						}
					}
								
				}
			else if((char)direction =='a')
			{
				for(int i=0;i<levelPolicy.getBoxes().size();i++)//check all boxes
					{
						if ((levelPolicy.getBoxes().get(i).getX()==x)&& 
								(levelPolicy.getBoxes().get(i).getY()==y-1)) //if there is a box on way
						{
							if(levelstr[x][y-2].charAt(0)==targetsymbol) //can move both
								return true;
							else
								return false;
						}
					}
				}
			else if((char)direction =='d')
			{
				for(int i=0;i<levelPolicy.getBoxes().size();i++)//check all boxes
					{
						if ((levelPolicy.getBoxes().get(i).getX()== x)&& 
								(levelPolicy.getBoxes().get(i).getY()==y+1)) //if there is a box on way
						{
							if(levelstr[x][y+2].charAt(0)==targetsymbol) //can move both
								return true;
							else
								return false;
						}
					}
				}
						
			return false;
		}
		public boolean VonwayE(Level levelPolicy,char direction) //if there is box on target next step Empty
		{
			int y =levelPolicy.getSokoCharas().get(0).getY();
			int x=levelPolicy.getSokoCharas().get(0).getX();
			String [][]levelstr=levelPolicy.makestring();
			
			
			if((char)direction =='w')
			{
			for(int i=0;i<levelPolicy.getBoxOnTareget().size();i++)//check all boxes
				{
					if ((levelPolicy.getBoxOnTareget().get(i).getX()== x-1)&& 
							(levelPolicy.getBoxOnTareget().get(i).getY()==y)) //if there is a box on way
					{
						if(levelstr[x-2][y].equals(" ")) //can move both
							return true;
						else
							return false;
					}
				}
							
			}
			else if((char)direction =='s')
			{
				for(int i=0;i<levelPolicy.getBoxOnTareget().size();i++)//check all boxes
					{
						if ((levelPolicy.getBoxOnTareget().get(i).getX()== x+1)&& 
								(levelPolicy.getBoxOnTareget().get(i).getY()==y)) //if there is a box on way
						{
							if(levelstr[x+2][y].equals(" "))//can move both
								return true;
							else
								return false;
						}
					}
								
				}
			else if((char)direction =='a')
			{
				for(int i=0;i<levelPolicy.getBoxOnTareget().size();i++)//check all boxes
					{
						if ((levelPolicy.getBoxOnTareget().get(i).getX()==x)&& 
								(levelPolicy.getBoxOnTareget().get(i).getY()==y-1)) //if there is a box on way
						{
							if(levelstr[x][y-2].equals(" ")) //can move both
								return true;
							else
								return false;
						}
					}
				}
			else if((char)direction =='d')
			{
				for(int i=0;i<levelPolicy.getBoxOnTareget().size();i++)//check all boxes
					{
						if ((levelPolicy.getBoxOnTareget().get(i).getX()== x)&& 
								(levelPolicy.getBoxOnTareget().get(i).getY()==y+1)) //if there is a box on way
						{
							if(levelstr[x][y+2].equals(" ")) //can move both
								return true;
							else
								return false;
						}
					}
				}
						
			return false;		
		}
		
		public boolean VonwayT(Level levelPolicy,char direction) //if there is box on target next step and Target after
		{
			int y =levelPolicy.getSokoCharas().get(0).getY();
			int x=levelPolicy.getSokoCharas().get(0).getX();
			char boxontargetsymbol = levelPolicy.getBoxOnTareget().get(0).boxesontargetsymbol();
			String [][]levelstr=levelPolicy.makestring();
			
			
			if((char)direction =='w')
			{
			for(int i=0;i<levelPolicy.getBoxOnTareget().size();i++)//check all boxes
				{
					if ((levelPolicy.getBoxOnTareget().get(i).getX()== x-1)&& 
							(levelPolicy.getBoxOnTareget().get(i).getY()==y)) //if there is a box on way
					{
						if(levelstr[x-2][y].charAt(0)==boxontargetsymbol) //can move both
							return true;
						else
							return false;
					}
				}
							
			}
			else if((char)direction =='s')
			{
				for(int i=0;i<levelPolicy.getBoxOnTareget().size();i++)//check all boxes
					{
						if ((levelPolicy.getBoxOnTareget().get(i).getX()== x+1)&& 
								(levelPolicy.getBoxOnTareget().get(i).getY()==y)) //if there is a box on way
						{
							if(levelstr[x+2][y].charAt(0)==boxontargetsymbol) //can move both
								return true;
							else
								return false;
						}
					}
								
				}
			else if((char)direction =='a')
			{
				for(int i=0;i<levelPolicy.getBoxOnTareget().size();i++)//check all boxes
					{
						if ((levelPolicy.getBoxOnTareget().get(i).getX()==x)&& 
								(levelPolicy.getBoxOnTareget().get(i).getY()==y-1)) //if there is a box on way
						{
							if(levelstr[x][y-2].charAt(0)==boxontargetsymbol) //can move both
								return true;
							else
								return false;
						}
					}
				}
			else if((char)direction =='d')
			{
				for(int i=0;i<levelPolicy.getBoxOnTareget().size();i++)//check all boxes
					{
						if ((levelPolicy.getBoxOnTareget().get(i).getX()== x)&& 
								(levelPolicy.getBoxOnTareget().get(i).getY()==y+1)) //if there is a box on way
						{
							if(levelstr[x][y+2].charAt(0)==boxontargetsymbol) //can move both
								return true;
							else
								return false;
						}
					}
				}
						
			return false;		
		}
				
		public boolean CanMove(Level levelPolicy,char direction)
		{
			int y =levelPolicy.getSokoCharas().get(0).getY();
			int x=levelPolicy.getSokoCharas().get(0).getX();
			String [][]levelstr=levelPolicy.makestring();
		
			
			char targetsymbol = levelPolicy.getTargets().get(0).getTargetsymbol();
			
			if((char)direction =='w')//move up
			{
				if((levelstr[x-1][y].equals(" "))||(levelstr[x-1][y].charAt(0)==targetsymbol))
					return true;
			}
			else if((char)direction =='s')//move down
			{
				if((levelstr[x+1][y].equals(" "))||(levelstr[x+1][y].charAt(0)==targetsymbol))
					return true;
			}
			else if((char)direction =='a')//move left
			{
				if((levelstr[x][y-1].equals(" "))||(levelstr[x][y-1].charAt(0)==targetsymbol))
					return true;
			}
			else if((char)direction =='d')//move right
			{
				if((levelstr[x][y+1].equals(" "))||(levelstr[x][y+1].charAt(0)==targetsymbol))
					return true;
			}
			
			return false;
					
		}
}

