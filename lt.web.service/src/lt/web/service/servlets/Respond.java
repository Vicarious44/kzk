package lt.web.service.servlets;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;

import lt.web.service.dao.ResponseFilter;


@Path("/respond")
public class Respond extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3981659810295970583L;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Yep";
	}

	// This method is called if XML is request
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<Respond> Yep" + "</Respond>";
	}

	// This method is called if HTML is request
	@GET
	@ResponseFilter
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Yep" + "</title>" + "<body><h1>"
				+ "Nope" + "</body></h1>" + "</html> ";
	}
}
