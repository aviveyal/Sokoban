package run;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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



public class Run {

	public static FileInputStream In;
		
	public static void main(String[] args) throws IOException {
		
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		
			if(s.startsWith("load "))//all methods can take action just if a level loaded first
				{
					Load L= new Load(s.substring(s.lastIndexOf(' ') + 1));
					Level newlevel =L.execute();
					
						while (!s.equals(true))
							{
							
							br = new BufferedReader(new InputStreamReader(System.in));
							s = br.readLine();
							
									if(s.equals("display")) //can only occur after a level has been loaded
									{
									Display D = new Display(newlevel);
									D.execute();	
									}
									else if(s.startsWith("move")) //can only occur after a level has been loaded
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
									else if(s.startsWith("save "))
									{
										Save S = new Save (s.substring(s.lastIndexOf(' ') + 1),newlevel);
										S.execute();
									}
									else if (s.equals("exit"))
									{
										Exit exit = new Exit();
										exit.execute();
									}
									
							}
				
				}
			else
			{
			System.out.println("File not found! , try load levels/levelX (X = level number)");	
			}
		
}
		
	
}
		 
	
	


