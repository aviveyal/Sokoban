package boot;

import Database.SokobanDBManager;
import Database.Users;

public class Run {

	


	

		public static void main(String[] args) {

			SokobanDBManager DB= new SokobanDBManager();
			Users s = new Users("aviv eyal");
			DB.addUser(s);
		}

	


}
