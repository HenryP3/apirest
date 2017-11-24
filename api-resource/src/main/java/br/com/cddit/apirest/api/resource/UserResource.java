package br.com.cddit.apirest.api.resource;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(ResourceDefinitions.USER_PATH)
public class UserResource {
	private Logger logger = LoggerFactory.getLogger(getClass());

	// @Inject
	// private UserService service;
	//
	// @Inject
	// Validator validator;
	//
	// @Override
	// @POST
	// public Response add(final String body) {
	// logger.debug("Adding a new user with body {}", body);
	// service.alreadyExistsByUsername("");
	// return Response.status(200).build();
	// }

	@Path("/hello")
	@GET
	@PermitAll
	public Response hello() {
		return Response.status(200).entity("you are welcome").build();
	}

}
