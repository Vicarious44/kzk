package lt.web.service.dao;

import javax.ws.rs.DELETE;
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
import lt.web.service.filter.AllowAllFilter;
import lt.web.service.filter.AllowOnlyOwnerFilter;
import lt.web.service.filter.SecureFilter;
import lt.web.service.model.FormaDuomenys;
import lt.web.service.model.FormaSablonas;
import lt.web.service.model.User;

@Path("/users")
public class UserResource {
	
	@Context ContainerRequestContext ctx;
	@Context SecurityContext sctx;
	
	private UserDao userdao;
	private FormaDao formadao;
	
	public UserResource() {
		userdao = DaoFactory.getUserDao(PersistanceType.DB);
		formadao = DaoFactory.getFormaDao(PersistanceType.DB);
	}
	
	//bus istrintas
	@GET
	@Path("count")
	@AllowAllFilter
	@Produces(MediaType.TEXT_PLAIN)
	public Long getUserCount() {
		return userdao.countUsers();
	}
	
	//bus istrintas
	@GET 
	@SecureFilter
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUserList(){
		return userdao.getAllUsers();
	}
		
	
	//bus istrintas
	@GET
	@Path("{id}")
	@SecureFilter
	@AllowOnlyOwnerFilter
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") String username) {
		return userdao.getUser(username);
	}
	
	@POST
	@SecureFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User user) { // kurti gali betkas
		return userdao.createUser(user);
	}
	
	//gali naudotis tik savininkas
	@PUT
	@Path("{id}")
	@SecureFilter
	@AllowOnlyOwnerFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUser(User user) {
		return userdao.updateUser(user);
	}
	
	@POST
	@Path("{id}/templates")
	@SecureFilter
	@AllowOnlyOwnerFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public FormaSablonas createTemplate(@PathParam("id") String id, FormaSablonas temp){
		return formadao.createTemplate(id, temp);
	}
	
	@PUT
	@Path("{id}/templates/{tempId}")
	@SecureFilter
	@AllowOnlyOwnerFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public FormaSablonas updateTemplate(@PathParam("id") String id, @PathParam("tempId") int tempid, FormaSablonas temp){
		return formadao.updateTemplate(id, tempid, temp);
	}
	
	@GET
	@Path("{id}/templates")
	@SecureFilter
	@AllowAllFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<FormaSablonas> getAllTemplates(@PathParam("id") String id){
		return formadao.getTemplateList(id);
	}
	
	@GET
	@Path("{id}/templates/{tempId}")
	@SecureFilter
	@AllowAllFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public FormaSablonas getTemplate(@PathParam("id") String id, @PathParam("tempId") int tempid){
		return formadao.getTemplate(id, tempid);
	}
	
	@DELETE
	@Path("{id}/templates/{tempId}")
	@SecureFilter
	@AllowOnlyOwnerFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public FormaSablonas deleteTemplate(@PathParam("id") String id, @PathParam("tempId") int tempid){
		return formadao.deleteTemplate(id, tempid);
	}
	
	@POST
	@Path("{id}/templates/{tempId}/duom")
	@SecureFilter
	@AllowAllFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public FormaDuomenys createDuom(@PathParam("id") String id, @PathParam("tempId") int tempid, FormaDuomenys duom){
		return formadao.createData(id, tempid, duom);
	}
	
	@PUT
	@Path("{id}/templates/{tempId}/duom/{duomId}")
	@SecureFilter
	@AllowAllFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public FormaDuomenys createData(@PathParam("id") String id, @PathParam("tempId") int tempid, @PathParam("duomId") int duomid, FormaDuomenys duom){
		return formadao.updateData(id, tempid, duomid, duom);
	}
	
	@GET
	@Path("{id}/templates/{tempId}/duom")
	@SecureFilter
	@AllowAllFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<FormaDuomenys> getAllData(@PathParam("id") String id, @PathParam("tampId") int tempid){
		return formadao.getDataList(id, tempid);
	}
	
	@GET
	@Path("{id}/templates/{tempId}/duom/{duomId}")
	@SecureFilter
	@AllowAllFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public FormaDuomenys getData(@PathParam("id") String id, @PathParam("tempId") int tempid, @PathParam("duomId") int duomid){
		return formadao.getData(id, tempid, duomid);
	}
	
	@DELETE
	@Path("{id}/templates/{tempId}/duom/{duomId}")
	@SecureFilter
	@AllowOnlyOwnerFilter
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public FormaDuomenys deleteData(@PathParam("id") String id, @PathParam("tempId") int tempid, @PathParam("duomId") int duomid){
		return formadao.deleteData(id, tempid, duomid);
	}

}
