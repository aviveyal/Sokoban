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
	@Column(name="usercode")
	private int usercode;
	@Id
	@Column(name="levelcode")
	private int levelcode;
	
	@Column(name="time")
	private String time;
	
	@Column(name="steps")
	private int steps;


	
	public int getUsercode() {
		return usercode;
	}




	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}




	public int getLevelcode() {
		return levelcode;
	}




	public void setLevelcode(int levelcode) {
		this.levelcode = levelcode;
	}




	public String getTime() {
		return time;
	}




	public void setTime(String time) {
		this.time = time;
	}




	public int getSteps() {
		return steps;
	}




	public void setSteps(int steps) {
		this.steps = steps;
	}




	public Scores(Users U , LevelsDB L ,String T , int steps)
	{
		this.usercode =U.getUsercode();
		this.levelcode =L.getLevelcode();
		this.time=T;
		this.steps=steps;
	}
	
	
}
