package br.com.cddit.integrationtest.resource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.URL;

import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.cddit.integrationtest.utils.ArquillianTestUtils;
import br.com.cddit.integrationtest.utils.ResourceClient;

@RunWith(Arquillian.class)
public class UserResourceIntTest {

	@ArquillianResource
	URL url;
	private static final String PATH_RESOURCE = "/api/users"; //
	// ResourceDefinitions.USER_PATH;
	// private static final String PATH_RESOURCE = "/users"; //
	// ResourceDefinitions.USER_PATH;
	private ResourceClient resourceClient;

	@Deployment
	public static WebArchive createDeployment() {
		return ArquillianTestUtils.createDeploymentArchive();
	}

	@Before
	public void initTestCase() {
		this.resourceClient = new ResourceClient(url);
		//
		// a ideia do codigo abaixo eh a fazer um cleanup no banco de dados ou inserir
		// uma massa de dados
		// resourceClient.resourcePath("/DB").delete();
		// resourceClient.resourcePath("DB" +
		// ResourceDefinitions.USER_PATH).postWithContent("");
		// resourceClient.user(new User());
	}

	@Test
	@RunAsClient
	public void addValidUserAndFindIt() {
		final Response response = resourceClient.resourcePath("/users/hello").get();

		assertThat(response.getStatus(), is(equalTo(200)));

		// user fixture
		// call url
		// compara resultado com fixture
	}
}
