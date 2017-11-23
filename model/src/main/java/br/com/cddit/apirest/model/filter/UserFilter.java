package br.com.cddit.apirest.model.filter;

import br.com.cddit.apirest.model.common.GenericFilter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserFilter extends GenericFilter {
	private String username;

}