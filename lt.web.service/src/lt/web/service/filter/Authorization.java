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
		if(user.getPswhash().equals(password))
			return true;
		return false;
	}
	
	public boolean isAccessGranted(String username, String targetUsername){
		if(username.equals(targetUsername))
			return true;
		return false;
	}
	
	public boolean authorized(ContainerRequestContext ctx ,String param){
		//System.out.println(res.toString());
				//respcont.getHeaders().add("Secret", "none");
				String temp = ctx.getHeaders().getFirst("authorization");
				//System.out.println(temp);
				String basic = new String(DatatypeConverter.parseBase64Binary(temp.substring(6)));
				StringTokenizer st = new StringTokenizer(basic,":");
				String username = ""+st.nextToken();
				String pswd = ""+st.nextToken();
				//System.out.println(ctx.getHeaders());
				//System.out.println(username + " " + pswd);
				if(this.isPasswordCorrect(username, pswd) && username.equals(param))
					return true;
				//	ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Incorrect password/resource").build());
				System.out.println(ctx.getProperty("id"));
		return false;
	}
}
