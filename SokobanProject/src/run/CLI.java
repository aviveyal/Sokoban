package run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Commands.Display;
import Commands.Exit;
import Commands.Load;
import Commands.Move;
import Commands.MoveDown;
import Commands.MoveLeft;
import Commands.MoveRight;
import Commands.MoveUp;
import Commands.Save;
import levels.Level;



public class CLI { 
	
	
	

	public static void main(String[] args) throws IOException {
		
		
		boolean loadedlevel=false;
		Level newlevel =new Level();
			
					
						while (true)
							{
							System.out.println(">load filename");
							System.out.println(">display");
							System.out.println(">move {up,dpwn,right,left}");
							System.out.println(">save filename");
							System.out.println(">exit");
							
							BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
							String s=br.readLine();
							
									
							
									 if (s.startsWith("load "))//all methods can take action just if a level loaded first
										{
										
										Load L= new Load(s.substring(s.lastIndexOf(' ') + 1));
										 newlevel=L.execute();
										 loadedlevel =true;
										}
									
									else if(s.equals("display")) //can only occur after a level has been loaded
									{
										if(loadedlevel==true)
										{
										Display D = new Display(newlevel);
										D.execute();
										}
										else
											System.out.println("\nFirst load a level!\n");
									}
									else if(s.startsWith("move ")) //can only occur after a level has been loaded
									{
										if(loadedlevel==true)
										{
												if(s.substring(s.lastIndexOf(' ') + 1).equals("up"))
												{
													Move M = new MoveUp(newlevel);
													newlevel=M.execute();
												
												}
												else if(s.substring(s.lastIndexOf(' ') + 1).equals("down"))
												{
													Move M = new MoveDown(newlevel);
													newlevel=M.execute();
												}
												else if(s.substring(s.lastIndexOf(' ') + 1).equals("right"))
												{
													Move M = new MoveRight(newlevel);
													newlevel=M.execute();
												}
												else if(s.substring(s.lastIndexOf(' ') + 1).equals("left"))
												{
													Move M = new MoveLeft(newlevel);
													newlevel=M.execute();
												}
										}
										else
											System.out.println("\nFirst load a level!\n");
																													
									}
									else if(s.startsWith("save "))
									{
										if(loadedlevel==true)
										{
										Save S = new Save (s.substring(s.lastIndexOf(' ') + 1),newlevel);
										S.execute();
										}
										else
											System.out.println("\nFirst load a level!\n");
									}
									else if (s.equals("exit"))
									{
										Exit exit = new Exit();
										exit.execute();
									}
									else
									{
										System.out.println("\nUnknown method - try again!\n");
									}
							}
				
			
					
		
	
		
			//make serialized date file 
			
		/*
		 
		Level L= new Level();
		 try {
	         FileOutputStream fileOut =
	         new FileOutputStream("levels/Level.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(L);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in levels/Level.ser");
	      }
		 catch(IOException i) 
		 {
	         i.printStackTrace();  
		 }
		 
		*/
		
				
		
}
}
	
	


