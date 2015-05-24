package lt.web.service.model;

//import java.io.Serializable;

//public class User implements Serializable{
public class User {
	//private static final long serialVersionUID = 2337304937447105145L;
	
	String username;
	String password;

	public User(String x, String y) {
		this.username = x;
		this.password = y;
	}
	
	public User(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
