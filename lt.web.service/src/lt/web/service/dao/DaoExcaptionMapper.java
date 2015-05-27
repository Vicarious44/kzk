package lt.web.service.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import lt.web.service.dao.DaoException;
import lt.web.service.dao.DaoException.Type;

/**
 * Mapping DAO exception to responses.
 * 
 * @author vaidas
 *
 */
@Provider
public class DaoExcaptionMapper implements ExceptionMapper<DaoException> {
	private final Map<Type, Status> typeStatusMapping;

	public DaoExcaptionMapper() {
		typeStatusMapping = new HashMap<>();
		typeStatusMapping.put(Type.NO_DATA, Status.NOT_FOUND);
		typeStatusMapping.put(Type.ERROR, Status.INTERNAL_SERVER_ERROR);
	}

	@Override
	public Response toResponse(DaoException exception) {
		return Response.status(typeStatusMapping.get(exception.getType()))
				.entity(exception.getMessage()).build();
	}

}
