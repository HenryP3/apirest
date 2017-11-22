package api.br.com.cddit.apirest.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class CrudResource<T> {

	@POST
	public Response add(final String body) {
		return null;
	}

	@PUT
	public Response update(final String body) {
		return null;
	}

	@GET
	@Path("{id}")
	public T findById(@PathParam("id") final int id) {
		return null;
	}

	@DELETE
	@Path("{id}")
	public T delete(@PathParam("id") final int id) {
		return null;
	}
}
