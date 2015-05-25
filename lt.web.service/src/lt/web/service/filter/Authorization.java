package lt.web.service.filter;

import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.xml.bind.DatatypeConverter;

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
	
	public String getUsername(ContainerRequestContext ctx){
		String temp = ctx.getHeaders().getFirst("authorization");
		String basic = new String(DatatypeConverter.parseBase64Binary(temp.substring(6)));
		StringTokenizer st = new StringTokenizer(basic,":");
		String username = ""+st.nextToken();
		return username;
	}
}
