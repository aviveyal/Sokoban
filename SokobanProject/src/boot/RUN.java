package boot;


import Database.SokobanDBManager;
import Database.Users;
import view.MainWindowController;


public class RUN {
	
	
	public static void main(String[] args) {
		System.out.println("hey");
		SokobanDBManager DB= new SokobanDBManager();
		
		DB.showTopTenUserlex("aviv");
		

	}

}
