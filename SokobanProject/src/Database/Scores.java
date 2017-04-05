package Database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Scores")
public class Scores implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="username")
	private String username;
	@Id
	@Column(name="levelname")
	private String levelname;
	
	@Column(name="time")
	private String time;
	
	@Column(name="steps")
	private String steps;


	
	


	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getLevelname() {
		return levelname;
	}




	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}




	public String getTime() {
		return time;
	}




	public void setTime(String time) {
		this.time = time;
	}




	public String getSteps() {
		return steps;
	}




	public void setSteps(String steps) {
		this.steps = steps;
	}




	public Scores(Users U , LevelsDB L ,String T , String steps)
	{
		this.username =U.getFullname();
		this.levelname =L.getLevelname();
		this.time=T;
		this.steps=steps;
	}
	
	
}
