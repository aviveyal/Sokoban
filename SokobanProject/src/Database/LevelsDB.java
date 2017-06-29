package Database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
/**
 * 
 * @author Aviv Eyal
 *table of all levels 
 */
@Entity(name="Levels")
public class LevelsDB {

	@OneToMany()
	@JoinColumn(name="levelcode")
	private List<Scores> scores; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="levelcode")
	private int levelcode;
	
	@Column(name="LevelName")
	private String levelname;

	
	
	public int getLevelcode() {
		return levelcode;
	}

	public void setLevelcode(int levelcode) {
		this.levelcode = levelcode;
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
	public LevelsDB() {
		
		scores = new ArrayList<Scores>();
		
	}
	@Override
	public String toString()
	{
		return "Level [level code=" + levelcode + ", levelname=" + levelname + "]";
	}
	
}
