package lt.web.service.dao;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.bind.DatatypeConverter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@ResponseFilter
@Provider
public class ResponseFilterImpl implements ContainerRequestFilter {
	
	Authorization auth = new Authorization();
	
	@Override
	public void filter(ContainerRequestContext respcont) throws IOException {
		respcont.getHeaders().add("Secret", "none");
		String temp = respcont.getHeaders().getFirst("authorization");
		System.out.println(temp);
		String basic = new String(DatatypeConverter.parseBase64Binary(temp.substring(6)));
		StringTokenizer st = new StringTokenizer(basic,":");
		String username = ""+st.nextToken();
		String pswd = ""+st.nextToken();
		System.out.println(respcont.getHeaders());
		System.out.println(username + "" + pswd);
		if(!auth.isPasswordCorrect(username, pswd))
			respcont.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Incorrect password").build());
		System.out.println(respcont.getProperty("id"));
	}
	
}
