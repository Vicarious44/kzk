package lt.web.service.dao;

import lt.web.service.dao.DaoFactory.PersistanceType;

public class Authorization {
	
	static private UserDao userdao;
	
	Authorization(){
		userdao = DaoFactory.getUserDao(PersistanceType.DB);
	}
	
	public boolean isPasswordCorrect(String username, String password){
		User user = userdao.getUser(username);
		if(user.pswhash.equals(password))
			return true;
		return false;
	}
	
	public boolean isAccessGranted(String username, String targetUsername){
		if(username.equals(targetUsername))
			return true;
		return false;
	}
}
