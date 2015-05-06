package lt.web.service.dao;

//import java.io.Serializable;

//public class User implements Serializable{
public class User {
	//private static final long serialVersionUID = 2337304937447105145L;
	
	String username;
	String pswhash;

	User(String x, String y) {
		this.username = x;
		this.pswhash = y;
	}
	
	public User(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPswhash() {
		return pswhash;
	}

	public void setPswhash(String pswhash) {
		this.pswhash = pswhash;
	}

}
