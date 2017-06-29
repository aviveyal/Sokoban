package Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "Users")
public class Users {

	@OneToMany()
	@JoinColumn(name = "usercode")
	private List<Scores> scores;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usercode")
	private int usercode;

	@Column(name = "fullname")
	private String fullname;

	public int getUsercode() {
		return usercode;
	}

	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Users(String fullname) {
		super();
		this.fullname = fullname;
		scores = new ArrayList<Scores>();
	}

	public Users() {

		scores = new ArrayList<Scores>();
	}

	@Override
	public String toString() {
		return "User [user code=" + usercode + ", fullname=" + fullname + "]";
	}

}
