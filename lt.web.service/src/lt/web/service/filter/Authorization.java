package lt.web.service.filter;

import lt.web.service.dao.DaoFactory;
import lt.web.service.dao.UserDao;
import lt.web.service.dao.DaoFactory.PersistanceType;
import lt.web.service.model.User;

public class Authorization {
	
	static private UserDao userdao;
	
	public Authorization(){
		userdao = DaoFactory.getUserDao(PersistanceType.DB);
	}
	
	public boolean isPasswordCorrect(String username, String password){
		User user = userdao.getUser(username);
		if(user.getPassword().equals(password))
			return true;
		return false;
	}
}
