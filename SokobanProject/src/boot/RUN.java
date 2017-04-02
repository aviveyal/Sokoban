package boot;

import Database.SokobanDBManager;
import Database.Users;

public class RUN {

	public static void main(String[] args) {
		System.out.println("hey");
		SokobanDBManager DB= new SokobanDBManager();
		Users u = new Users("aviv eyal");
		DB.addUser(u);

	}

}
