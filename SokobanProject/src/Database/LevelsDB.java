package Database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Levels")
public class LevelsDB {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int LevelCode;
	
	@Column(name="LevelName")
	private String levelname;

	public int getLevelcode() {
		return LevelCode;
	}

	public void setLevelcode(int levelcode) {
		this.LevelCode = levelcode;
	}

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public LevelsDB(String levelname) {		
		this.levelname = levelname;
		
	}
	
}
