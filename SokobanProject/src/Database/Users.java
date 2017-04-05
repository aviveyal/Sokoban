package Database;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.Data.LevelLoader;
import model.Data.MyObjectLevelLoader;
import model.Data.MyTextLevelLoader;
import model.Data.MyXMLLevelLoader;

@Entity(name="Users")
public class Users {


	
	/*Adding elements to HashMap*/
    
	@Id
	@Column(name="fullname")
	private String fullname;

	

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
