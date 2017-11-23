package br.com.cddit.apirest.model.fixture;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

import br.com.cddit.apirest.model.User;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class UserFixture implements TemplateLoader {

	@Override
	public void load() {

		Fixture.of(User.class).addTemplate("validUser", new Rule() {
			{
				add("version", 0L);
				add("username", uniqueRandom("userwithmorethan7chars",
						"userwithmorethan7charsv2",
						"userwithmorethan7charsv3",
						"userwithmorethan7charsv4",
						"userwithmorethan7charsv3d",
						"userwithmorethan7charsv4r",
						"userwithmorethan7charsv3u",
						"userwithmorethan7charsv4j",
						"userwithmorethan7charsv5",
						"user with large enough name" + new Random().nextInt(900)));
				add("password", "encryptar");
			}
		});

		Fixture.of(User.class).addTemplate("invalidUserFieldUsernameHasNoMinLength", new Rule() {
			{
				add("version", 0L);
				add("username", random("user1", "user2"));
				add("password", StringUtils.repeat("789", 50));
			}
		});
	}

}
