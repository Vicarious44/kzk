package lt.web.service.dao;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.bind.DatatypeConverter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import lt.web.service.filter.Authorization;

@ResponseFilter
@Provider
public class ResponseFilterImpl implements ContainerRequestFilter {
	
	Authorization auth = new Authorization();
	@Override
	public void filter(ContainerRequestContext respcont) throws IOException {
		respcont.getHeaders().add("Secret", "none");//cia tik taip sau
		String h = respcont.getUriInfo().getRequestUri().getPath();
		String temp = respcont.getHeaders().getFirst("authorization");
		String basic = new String(DatatypeConverter.parseBase64Binary(temp.substring(6)));
		StringTokenizer st = new StringTokenizer(basic,":");
		String username = ""+st.nextToken();
		String pswd = ""+st.nextToken();
		StringTokenizer st2 = new StringTokenizer(h,"//");
		for(int i = 0; i < 3; i++){
			st2.nextToken();
		}
		String res = ""+st2.nextToken();
		if(!auth.isPasswordCorrect(username, pswd)|| !username.equals(res))
			respcont.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Incorrect password").build());
	}
	
}
