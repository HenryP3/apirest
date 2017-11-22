package api.br.com.cddit.apirest.api.resource;

import javax.ws.rs.Path;

import br.com.cddit.apirest.service.UserService;

@Path("/users")
public class UserResource extends CrudResource {

	// private Logger logger = LoggerFactory.getLogger(getClass());
	UserService service;

}
