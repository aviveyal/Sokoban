package boot;

import java.util.ArrayList;
import java.util.HashMap;

import Database.LevelsDB;
import Database.Scores;
import Database.SokobanDBManager;
import Database.Users;
import model.Data.LevelLoader;
import model.Data.MyObjectLevelLoader;
import model.Data.MyTextLevelLoader;
import model.Data.MyXMLLevelLoader;
import model.Data.Wall;

public class RUN {
	
	
	public static void main(String[] args) {
		System.out.println("hey");
		SokobanDBManager DB= new SokobanDBManager();
		Users u = new Users("shimi");
		DB.addUser(u);
				
		LevelsDB level = new LevelsDB("thehardest");
		DB.addLevel(level);
		
		Scores score = new Scores(u,level,"10:12","23");
		DB.addScores(score);

	}

}
