package lt.web.service.dao;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

import javax.ws.rs.PathParam;

import lt.web.service.dao.DaoFactory.PersistanceType;
import lt.web.service.filter.Authorization;
import lt.web.service.model.User;

@Path("/users")
public class UserResource {
	
	@Context ContainerRequestContext ctx;
	
	private UserDao userdao;
	private Authorization auth;
	
	public UserResource() {
		userdao = DaoFactory.getUserDao(PersistanceType.DB);
		auth = new Authorization();
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public Long getUserCount() { // bus istrintas visai
		return userdao.countUsers();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") String username) {
		if(!auth.authorized(ctx, username)){ // gali prieiti tik prie saves
			return null;// pakeisti i errora koki nors
		}
		return userdao.getUser(username);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User user) { // kurti gali betkas
		return userdao.createUser(user);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUser(User user) {
		if(!auth.authorized(ctx, user.getUsername())){ // gali updatint tik savo profili
			return null;// pakeisti i errora koki nors
		}
		return userdao.updateUser(user);
	}
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUserList(){ // bus paskui istrintas visam
		return userdao.getAllUsers();
	}
	
}
