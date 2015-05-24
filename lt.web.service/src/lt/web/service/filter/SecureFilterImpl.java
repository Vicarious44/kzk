package lt.web.service.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@SecureFilter
public class SecureFilterImpl implements ContainerRequestFilter{
	
	@Override
	public void filter(ContainerRequestContext respcont) throws IOException {
		if(!respcont.getSecurityContext().isSecure()){
			System.out.println("secure filter aborted request");
			respcont.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Http not allowed, connect through https").build());	
		}
			
	}
}
