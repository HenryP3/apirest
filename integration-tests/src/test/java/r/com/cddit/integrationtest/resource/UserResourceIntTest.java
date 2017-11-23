package r.com.cddit.integrationtest.resource;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import api.br.com.cddit.apirest.api.resource.ResourceDefinitions;
import br.com.cddit.apirest.model.User;
import br.com.cddit.integrationtest.utils.ArquillianTestUtils;
import br.com.cddit.integrationtest.utils.ResourceClient;

@RunWith(Arquillian.class)
public class UserResourceIntTest {

	@ArquillianResource
	private URL url;
	private static final String PATH_RESOURCE = ResourceDefinitions.USER_PATH;
	private ResourceClient resourceClient;

	@Deployment
	public static WebArchive createDeployment() {
		return ArquillianTestUtils.createDeploymentArchive();
	}

	@Before
	public void initTestCase() {
		this.resourceClient = new ResourceClient(url);

		resourceClient.resourcePath("/DB").delete();
		resourceClient.resourcePath("DB" + ResourceDefinitions.USER_PATH).postWithContent("");
		resourceClient.user(new User());
	}

	@Test
	@RunAsClient
	public void addValidUserAndFindIt() {
		// user fixture
		// call url
		// compara resultado com fixture
	}
}
