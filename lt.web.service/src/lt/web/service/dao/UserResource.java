package lt.web.service.dao;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import javax.ws.rs.PathParam;

import lt.web.service.dao.DaoFactory.PersistanceType;

@Path("/users")
public class UserResource {
	private UserDao userdao;
	
	public UserResource() {
		userdao = DaoFactory.getUserDao(PersistanceType.DB);
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public Long getUserCount() {
		return userdao.countUsers();
	}
	
	@GET
	@ResponseFilter
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") String username) {
		System.out.println("dfhasdhmfhaasdf");
		return userdao.getUser(username);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User user) {
		System.out.print("got to herererere");
		System.out.println(user);
		return userdao.createUser(user);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUser(User user) {
		System.out.println("got to here too");
		return userdao.updateUser(user);
	}
	
	
	@GET
	@ResponseFilter
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUserList(){
		return userdao.getAllUsers();
	}
}
