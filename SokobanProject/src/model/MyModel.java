package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;


import model.Data.Level;

import model.modelCommands.Exit;
import model.modelCommands.Load;
import model.modelCommands.Move;
import model.modelCommands.MoveDown;
import model.modelCommands.MoveLeft;
import model.modelCommands.MoveRight;
import model.modelCommands.MoveUp;
import model.modelCommands.Save;
import view.MainWindowController;

public class MyModel extends Observable implements Model {

	private Level level;
	//public Socket socket; 
	boolean connected=false;
	@Override
	public Level getCurrentLevel() {
		
		return level;
	}

	@Override
	public void mMoveCommand(String direction) {
		switch(direction)	
		{
		case "up":
			Move mu = new MoveUp(this.level);
			this.SetLevel(mu.execute());
			break;
		case "down":
			Move md = new MoveDown(this.level);
			this.SetLevel(md.execute());
			break;
		case "left":
			Move ml = new MoveLeft(this.level);
			this.SetLevel(ml.execute());
			break;
		case "right":
			Move mr = new MoveRight(this.level);
			this.SetLevel(mr.execute());
			break;
		default:
			break;
		}
		
		this.setChanged();
		List<String> params=new LinkedList<String>();
		params.add("display");
		this.notifyObservers(params);		
	}

	@Override
	public void mLoadCommand(String filepath) throws IOException {
		
		//open socket
	/*	if(!connected)
		{
		try{
			socket = new Socket("127.0.0.1", 5555);
			System.out.println("connected to server");
			connected=true;
		} 
		catch (UnknownHostException e) {}
		catch (IOException e) {
			
		}
		}*/
		
			Load L = new Load(filepath);
			SetLevel(L.execute());
			setChanged();
			List<String> params=new LinkedList<String>();
			params.add("display");
			notifyObservers(params);
			
		
	}

	@Override
	public void mSaveCommand(String filepath) throws IOException {
		
		Save s = new Save(filepath,level);
		this.SetLevel(s.execute());		
		this.setChanged();

	}
	

	@Override
	public void SetLevel(Level level) {
		this.level=level;
	}

	@Override
	public void mExit() {
		Exit x = new Exit();
		x.execute();
		this.setChanged();
		
	}

	@Override
	public void SendToServer(String levelname,Socket socket) {
		
		
		try {	
			socket = new Socket("127.0.0.1", 5555);
			
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
						
			//send level info 
			outToServer.writeObject(levelname);
			System.out.println("sent level :" +levelname);
			char [][] leveldata= level.makestring();
			outToServer.writeObject(level.maxrowsize()+","+ level.maxcolumnsize()+"\n");
			String str= "";
			for(int i=0;i<leveldata.length;i++){
				for(int j=0;j<leveldata[i].length;j++)
				{
					str+=leveldata[i][j];
				}
				outToServer.writeObject(str+"\n");
				str="";
			}
			
			String solution = serverInput.readLine();
			System.out.println("Solution received from server: " + solution);
			List<String> params=new LinkedList<String>();
			
			//update the solution in gui
			params.add("getSolution");
			params.add(solution);
			setChanged();
			notifyObservers(params);
			
			outToServer.flush();
			serverInput.close();
			outToServer.close();
			socket.close();
		} 
		catch (UnknownHostException e) {/*...*/}
		catch (IOException e) {
		
		}
		
	}



	
}
