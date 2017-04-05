package Database;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ScoresKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int usercode;
	private int levelcode;
	
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
	public ScoresKey(int usercode, int levelcode) {
		super();
		this.usercode = usercode;
		this.levelcode = levelcode;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 31 * usercode + levelcode;	
	}
	public ScoresKey() {
		super();
	}
	
	
}