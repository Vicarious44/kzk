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
import javax.ws.rs.core.SecurityContext;

import java.util.List;

import javax.ws.rs.PathParam;

import lt.web.service.dao.DaoFactory.PersistanceType;
import lt.web.service.model.User;



@Path("/users")
public class UserResource {
	
	@Context ContainerRequestContext ctx;
	@Context SecurityContext sctx;
	
	private UserDao userdao;
	
	public UserResource() {
		userdao = DaoFactory.getUserDao(PersistanceType.DB);
	}
	
	//bus istrintas
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public Long getUserCount() {
		return userdao.countUsers();
	}
	
	//bus istrintas
	@GET
	@Path("{id}")
	@ResponseFilter
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") String username) {
		return userdao.getUser(username);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User user) { // kurti gali betkas
		return userdao.createUser(user);
	}
	
	//gali naudotis tik savininkas
	@PUT
	@Path("{id}")
	@ResponseFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUser(User user) {
		return userdao.updateUser(user);
	}
	
	//bus istrintas
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUserList(){
		return userdao.getAllUsers();
	}
	
}
