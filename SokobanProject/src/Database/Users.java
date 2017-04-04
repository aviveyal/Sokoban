package Database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Users")
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int usercode;
	
	@Column(name="fullname")
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
	
	public Users( String fullname) {		
		this.fullname = fullname;
		
	}
	
	
}
