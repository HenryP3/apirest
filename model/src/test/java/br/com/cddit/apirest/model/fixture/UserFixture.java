package br.com.cddit.apirest.model.fixture;

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
				add("username", random("userwithmorethan7chars", "user with large enough name"));
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
