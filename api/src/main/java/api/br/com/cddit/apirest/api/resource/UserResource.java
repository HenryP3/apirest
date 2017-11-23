package api.br.com.cddit.apirest.api.resource;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cddit.apirest.model.User;
import br.com.cddit.apirest.service.UserService;

@Path(ResourceDefinitions.USER_PATH)
public class UserResource extends CrudResource<User> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private UserService service;

	@Inject
	Validator validator;

	@Override
	@POST
	public Response add(final String body) {
		logger.debug("Adding a new user with body {}", body);
		service.alreadyExistsByUsername("");
		return Response.status(200).build();
	}

}
