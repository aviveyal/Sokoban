package levels;

public class MoveSoko {
	
	public Level playlevel;
	
	public MoveSoko(Level playlevel)
	{
		this.playlevel=playlevel;
	}
	
	public void MovePlayer(Level playlevel,int direction) {
		
		int y =playlevel.getSokoCharas().get(0).getY();// first soko -y
		int x = playlevel.getSokoCharas().get(0).getX();// first soko -x
		MySokobanPolicy Policy = new MySokobanPolicy(playlevel);
		/*
		 
		 the algorithm :
		 1. check if next step is empty -> move soko
		 2. check if there is box on target next 
		  		if yes -
		  				1. check if 2 steps next is empty - delete Box on target object , move soko move box
		  				2. check 2 steps next is another target -delete Box on target object,create new box on target, move soko move box
		 		if no -
		 				3.check if two steps next is a target - move soko , move box , create new Box on target object
		 				4.check if two steps forward is  empty - move soko , move box 
		  
		  
		  */
		 
		//can be written shorter - add another function and for each way to move change how much to move -function(w,x-1,y),function(s,x+1,y)
		
if((char)direction =='w')//move up
{
						
						if(Policy.CanMove(playlevel, 'w'))
						{
							
							for(int i=0;i<playlevel.getSokoCharas().size();i++)
							{
								if ((playlevel.getSokoCharas().get(i).getX()==x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
								{
									playlevel.getSokoCharas().get(i).position(x-1, y);
									i=playlevel.getSokoCharas().size();
								}	
							}
									
						}
									
						else if(Policy.VonwayT(playlevel, 'w'))
							{
									//remove current V , add new V next step
									for(int i =0 ; i<playlevel.getBoxOnTareget().size();i++)
									{
										if ((playlevel.getBoxOnTareget().get(i).getX()== x-1)&&(playlevel.getBoxOnTareget().get(i).getY()==y))
											{
												playlevel.getBoxOnTareget().remove(i);
												playlevel.getBoxOnTareget().add((new BoxOntarget(x-2, y)));
												i=playlevel.getBoxOnTareget().size();//stop loop
											}
									}
									
								//move box 
									for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
									{
										
											if ((playlevel.getBoxes().get(i).getX()== x-1)&&(playlevel.getBoxes().get(i).getY()==y))
											{
													playlevel.getBoxes().get(i).position(x-2, y);
													i=playlevel.getBoxes().size();//stop loop
											}
										
									}
									//move sokochar
									for(int i=0;i<playlevel.getSokoCharas().size();i++)
									{
										if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
										{
											playlevel.getSokoCharas().get(i).position(x-1, y);
											i=playlevel.getSokoCharas().size();
										}	
									}
							}
							
							else if(Policy.VonwayE(playlevel, 'w'))
							{
									//remove current V
									for(int i =0 ; i<playlevel.getBoxOnTareget().size();i++)
									{
										if ((playlevel.getBoxOnTareget().get(i).getX()== x-1)&&(playlevel.getBoxOnTareget().get(i).getY()==y))
											{
												playlevel.getBoxOnTareget().remove(i);
												i=playlevel.getBoxOnTareget().size();//stop loop
											}
										
									}
									
									//move box 
									for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
									{
										
											if ((playlevel.getBoxes().get(i).getX()== x-1)&&(playlevel.getBoxes().get(i).getY()==y))
											{
													playlevel.getBoxes().get(i).position(x-2, y);
													i=playlevel.getBoxes().size();//stop loop
											}
										
									}
									//move sokochar
									for(int i=0;i<playlevel.getSokoCharas().size();i++)
									{
										if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
										{
											playlevel.getSokoCharas().get(i).position(x-1, y);
											i=playlevel.getSokoCharas().size();
										}	
									}
						
							}
						
						else if (Policy.BoxOnTarget(playlevel, 'w'))
						{
						
							//move box 
							for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
							{
								if ((playlevel.getBoxes().get(i).getX()== x-1)&&(playlevel.getBoxes().get(i).getY()==y))//change the specific box position
								{
									
									playlevel.getBoxes().get(i).position(x-2, y);
									playlevel.getBoxOnTareget().add((new BoxOntarget(x-2, y)));//create new box on target -box arrived to the the target !
									i=playlevel.getBoxes().size();//stop loop
									
								}
							}
							//move sokochar
							for(int i=0;i<playlevel.getSokoCharas().size();i++)
							{
								if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
								{
									playlevel.getSokoCharas().get(i).position(x-1, y);
									i=playlevel.getSokoCharas().size();
								}	
							}
						}
						else if(Policy.BoxOnWay(playlevel,'w'))
						{
							//move box 
							for(int i=0;i<=playlevel.getBoxes().size();i++)//check all boxes
							{
								
									if ((playlevel.getBoxes().get(i).getX()== x-1)&&(playlevel.getBoxes().get(i).getY()==y))
									{
											
											playlevel.getBoxes().get(i).position(x-2, y);
											i=playlevel.getBoxes().size();//stop loop
									}
								
							}
							//move sokochar
							for(int i=0;i<=playlevel.getSokoCharas().size();i++)
							{
								if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
								{
									playlevel.getSokoCharas().get(i).position(x-1, y);
									i=playlevel.getSokoCharas().size();
								}	
							}
						
						}
			
}
			
				
else if((char)direction =='s')//move down
{
							if(Policy.CanMove(playlevel, 's'))
							{
							for(int i=0;i<playlevel.getSokoCharas().size();i++)
							{
								if ((playlevel.getSokoCharas().get(i).getX()==x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
								{
									playlevel.getSokoCharas().get(i).position(x+1, y);
									i=playlevel.getSokoCharas().size();
								}	
							}
									
						}
									
						else if(Policy.VonwayT(playlevel, 's'))
							{
									//remove current V , add new V next step
									for(int i =0 ; i<playlevel.getBoxOnTareget().size();i++)
									{
										if ((playlevel.getBoxOnTareget().get(i).getX()== x+1)&&(playlevel.getBoxOnTareget().get(i).getY()==y))
											{
												playlevel.getBoxOnTareget().remove(i);
												playlevel.getBoxOnTareget().add((new BoxOntarget(x+2, y)));
												i=playlevel.getBoxOnTareget().size();//stop loop
											}
									}
									
								//move box 
									for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
									{
										
											if ((playlevel.getBoxes().get(i).getX()== x+1)&&(playlevel.getBoxes().get(i).getY()==y))
											{
													playlevel.getBoxes().get(i).position(x+2, y);
													i=playlevel.getBoxes().size();//stop loop
											}
										
									}
									//move sokochar
									for(int i=0;i<playlevel.getSokoCharas().size();i++)
									{
										if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
										{
											playlevel.getSokoCharas().get(i).position(x+1, y);
											i=playlevel.getSokoCharas().size();
										}	
									}
							}
							
							else if(Policy.VonwayE(playlevel, 's'))
							{
									//remove current V
									for(int i =0 ; i<playlevel.getBoxOnTareget().size();i++)
									{
										if ((playlevel.getBoxOnTareget().get(i).getX()== x+1)&&(playlevel.getBoxOnTareget().get(i).getY()==y))
											{
												playlevel.getBoxOnTareget().remove(i);
												i=playlevel.getBoxOnTareget().size();//stop loop
											}
										
									}
									
									//move box 
									for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
									{
										
											if ((playlevel.getBoxes().get(i).getX()== x+1)&&(playlevel.getBoxes().get(i).getY()==y))
											{
													playlevel.getBoxes().get(i).position(x+2, y);
													i=playlevel.getBoxes().size();//stop loop
											}
										
									}
									//move sokochar
									for(int i=0;i<playlevel.getSokoCharas().size();i++)
									{
										if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
										{
											playlevel.getSokoCharas().get(i).position(x+1, y);
											i=playlevel.getSokoCharas().size();
										}	
									}
						
							}
						
						else if (Policy.BoxOnTarget(playlevel, 's'))
						{
						
							//move box 
							for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
							{
								if ((playlevel.getBoxes().get(i).getX()== x+1)&&(playlevel.getBoxes().get(i).getY()==y))//change the specific box position
								{
									
									playlevel.getBoxes().get(i).position(x+2, y);
									playlevel.getBoxOnTareget().add((new BoxOntarget(x+2, y)));//create new box on target -box arrived to the the target !
									i=playlevel.getBoxes().size();//stop loop
									
								}
							}
							//move sokochar
							for(int i=0;i<playlevel.getSokoCharas().size();i++)
							{
								if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
								{
									playlevel.getSokoCharas().get(i).position(x+1, y);
									i=playlevel.getSokoCharas().size();
								}	
							}
						}
						else if(Policy.BoxOnWay(playlevel,'s'))
						{
							//move box 
							for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
							{
								
									if ((playlevel.getBoxes().get(i).getX()== x+1)&&(playlevel.getBoxes().get(i).getY()==y))
									{
											playlevel.getBoxes().get(i).position(x+2, y);
											i=playlevel.getBoxes().size();//stop loop
									}
								
							}
							//move sokochar
							for(int i=0;i<playlevel.getSokoCharas().size();i++)
							{
								if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
								{
									playlevel.getSokoCharas().get(i).position(x+1, y);
									i=playlevel.getSokoCharas().size();
								}	
							}
						
						}
						
		}
		
		
else if((char)direction =='a')//move left
{
							if(Policy.CanMove(playlevel, 'a'))
							{
							for(int i=0;i<playlevel.getSokoCharas().size();i++)
							{
								if ((playlevel.getSokoCharas().get(i).getX()==x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
								{
									playlevel.getSokoCharas().get(i).position(x, y-1);
									i=playlevel.getSokoCharas().size();
								}	
							}
									
						}
									
						else if(Policy.VonwayT(playlevel, 'a'))
							{
									//remove current V , add new V next step
									for(int i =0 ; i<playlevel.getBoxOnTareget().size();i++)
									{
										if ((playlevel.getBoxOnTareget().get(i).getX()== x)&&(playlevel.getBoxOnTareget().get(i).getY()==y-1))
											{
												playlevel.getBoxOnTareget().remove(i);
												playlevel.getBoxOnTareget().add((new BoxOntarget(x, y-2)));
												i=playlevel.getBoxOnTareget().size();//stop loop
											}
									}
									
								//move box 
									for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
									{
										
											if ((playlevel.getBoxes().get(i).getX()== x)&&(playlevel.getBoxes().get(i).getY()==y-1))
											{
													playlevel.getBoxes().get(i).position(x, y-2);
													i=playlevel.getBoxes().size();//stop loop
											}
										
									}
									//move sokochar
									for(int i=0;i<playlevel.getSokoCharas().size();i++)
									{
										if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
										{
											playlevel.getSokoCharas().get(i).position(x, y-1);
											i=playlevel.getSokoCharas().size();
										}	
									}
							}
							
							else if(Policy.VonwayE(playlevel, 'a'))
							{
									//remove current V
									for(int i =0 ; i<playlevel.getBoxOnTareget().size();i++)
									{
										if ((playlevel.getBoxOnTareget().get(i).getX()== x)&&(playlevel.getBoxOnTareget().get(i).getY()==y-1))
											{
												playlevel.getBoxOnTareget().remove(i);
												i=playlevel.getBoxOnTareget().size();//stop loop
											}
										
									}
									
									//move box 
									for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
									{
										
											if ((playlevel.getBoxes().get(i).getX()== x)&&(playlevel.getBoxes().get(i).getY()==y-1))
											{
													playlevel.getBoxes().get(i).position(x, y-2);
													i=playlevel.getBoxes().size();//stop loop
											}
										
									}
									//move sokochar
									for(int i=0;i<playlevel.getSokoCharas().size();i++)
									{
										if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
										{
											playlevel.getSokoCharas().get(i).position(x, y-1);
											i=playlevel.getSokoCharas().size();
										}	
									}
						
							}
						
						else if (Policy.BoxOnTarget(playlevel, 'a'))
						{
						
							//move box 
							for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
							{
								if ((playlevel.getBoxes().get(i).getX()== x)&&(playlevel.getBoxes().get(i).getY()==y-1))//change the specific box position
								{
									
									playlevel.getBoxes().get(i).position(x, y-2);
									playlevel.getBoxOnTareget().add((new BoxOntarget(x, y-2)));//create new box on target -box arrived to the the target !
									i=playlevel.getBoxes().size();//stop loop
									
								}
							}
							//move sokochar
							for(int i=0;i<playlevel.getSokoCharas().size();i++)
							{
								if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
								{
									playlevel.getSokoCharas().get(i).position(x, y-1);
									i=playlevel.getSokoCharas().size();
								}	
							}
						}
						else if(Policy.BoxOnWay(playlevel,'a'))
						{
							//move box 
							for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
							{
								
									if ((playlevel.getBoxes().get(i).getX()== x)&&(playlevel.getBoxes().get(i).getY()==y-1))
									{
											playlevel.getBoxes().get(i).position(x, y-2);
											i=playlevel.getBoxes().size();//stop loop
									}
								
							}
							//move sokochar
							for(int i=0;i<playlevel.getSokoCharas().size();i++)
							{
								if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
								{
									playlevel.getSokoCharas().get(i).position(x, y-1);
									i=playlevel.getSokoCharas().size();
								}	
							}
						
						}
						
}

else if((char)direction =='d')//move right
{
							if(Policy.CanMove(playlevel, 'd'))
							{
							for(int i=0;i<playlevel.getSokoCharas().size();i++)
							{
								if ((playlevel.getSokoCharas().get(i).getX()==x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
								{
									playlevel.getSokoCharas().get(i).position(x, y+1);
									i=playlevel.getSokoCharas().size();
								}	
							}
									
						}
									
						else if(Policy.VonwayT(playlevel, 'd'))
							{
									//remove current V , add new V next step
									for(int i =0 ; i<playlevel.getBoxOnTareget().size();i++)
									{
										if ((playlevel.getBoxOnTareget().get(i).getX()== x)&&(playlevel.getBoxOnTareget().get(i).getY()==y+1))
											{
												playlevel.getBoxOnTareget().remove(i);
												playlevel.getBoxOnTareget().add((new BoxOntarget(x, y+2)));
												i=playlevel.getBoxOnTareget().size();//stop loop
											}
									}
									
								//move box 
									for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
									{
										
											if ((playlevel.getBoxes().get(i).getX()== x)&&(playlevel.getBoxes().get(i).getY()==y+1))
											{
													playlevel.getBoxes().get(i).position(x, y+2);
													i=playlevel.getBoxes().size();//stop loop
											}
										
									}
									//move sokochar
									for(int i=0;i<playlevel.getSokoCharas().size();i++)
									{
										if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
										{
											playlevel.getSokoCharas().get(i).position(x, y+1);
											i=playlevel.getSokoCharas().size();
										}	
									}
							}
							
							else if(Policy.VonwayE(playlevel, 'd'))
							{
									//remove current V
									for(int i =0 ; i<playlevel.getBoxOnTareget().size();i++)
									{
										if ((playlevel.getBoxOnTareget().get(i).getX()== x)&&(playlevel.getBoxOnTareget().get(i).getY()==y+1))
											{
												playlevel.getBoxOnTareget().remove(i);
												i=playlevel.getBoxOnTareget().size();//stop loop
											}
										
									}
									
									//move box 
									for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
									{
										
											if ((playlevel.getBoxes().get(i).getX()== x)&&(playlevel.getBoxes().get(i).getY()==y+1))
											{
													playlevel.getBoxes().get(i).position(x, y+2);
													i=playlevel.getBoxes().size();//stop loop
											}
										
									}
									//move sokochar
									for(int i=0;i<playlevel.getSokoCharas().size();i++)
									{
										if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
										{
											playlevel.getSokoCharas().get(i).position(x, y+1);
											i=playlevel.getSokoCharas().size();
										}	
									}
						
							}
						
						else if (Policy.BoxOnTarget(playlevel, 'd'))
						{
						
							//move box 
							for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
							{
								if ((playlevel.getBoxes().get(i).getX()== x)&&(playlevel.getBoxes().get(i).getY()==y+1))//change the specific box position
								{
									
									playlevel.getBoxes().get(i).position(x, y+2);
									playlevel.getBoxOnTareget().add((new BoxOntarget(x, y+2)));//create new box on target -box arrived to the the target !
									i=playlevel.getBoxes().size();//stop loop
									
								}
							}
							//move sokochar
							for(int i=0;i<playlevel.getSokoCharas().size();i++)
							{
								if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
								{
									playlevel.getSokoCharas().get(i).position(x, y+1);
									i=playlevel.getSokoCharas().size();
								}	
							}
						}
						else if(Policy.BoxOnWay(playlevel,'d'))
						{
							//move box 
							for(int i=0;i<playlevel.getBoxes().size();i++)//check all boxes
							{
								
									if ((playlevel.getBoxes().get(i).getX()== x)&&(playlevel.getBoxes().get(i).getY()==y+1))
									{
											playlevel.getBoxes().get(i).position(x, y+2);
											i=playlevel.getBoxes().size();//stop loop
									}
								
							}
							//move sokochar
							for(int i=0;i<playlevel.getSokoCharas().size();i++)
							{
								if ((playlevel.getSokoCharas().get(i).getX()== x)&&(playlevel.getSokoCharas().get(i).getY()==y))//change the specific soko position
								{
									playlevel.getSokoCharas().get(i).position(x, y+1);
									i=playlevel.getSokoCharas().size();
								}	
							}
						}
}
					
			
			
}
		
	
	
}




