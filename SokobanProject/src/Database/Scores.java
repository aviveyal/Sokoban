package Database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * 
 * @author Aviv Eyal
 *table of all scores - level id , user name , time and steps 
 */
@Entity(name = "Scores")
public class Scores {

	@EmbeddedId
	protected ScoresKey codes;

	@Column(name = "time")
	private String time;

	@Column(name = "steps")
	private String steps;

	public int getuserCodes() {
		return codes.getUsercode();
	}

	public int getlevelCodes() {
		return codes.getLevelcode();
	}

	public void setCodes(ScoresKey codes) {
		this.codes = codes;
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

	public Scores(int usercode, int levelcode, String Time, String steps) {
		this.codes = new ScoresKey(usercode, levelcode);
		this.time = Time;
		this.steps = steps;
	}

	@Override
	public String toString() {
		return "Scorecs [level code=" + codes.getLevelcode() + ", usercode=" + codes.getUsercode() + " time=" + time
				+ ", steps=" + steps + "]";
	}

	public Scores() {

	}

}
