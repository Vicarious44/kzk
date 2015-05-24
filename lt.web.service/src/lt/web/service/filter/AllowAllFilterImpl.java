package lt.web.service.filter;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

import lt.web.service.dao.AuthorizationFactory;

@Provider
@AllowAllFilter
public class AllowAllFilterImpl implements ContainerRequestFilter{
	Authorization auth = AuthorizationFactory.getAuth();
	@Override
	public void filter(ContainerRequestContext respcont) throws IOException {
		String temp = respcont.getHeaders().getFirst("authorization");
		String basic = new String(DatatypeConverter.parseBase64Binary(temp.substring(6)));
		StringTokenizer st = new StringTokenizer(basic,":");
		String username = ""+st.nextToken();
		String pswd = ""+st.nextToken();
		if(!auth.isPasswordCorrect(username, pswd))
			respcont.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Incorrect password/username").build());
	}
}
