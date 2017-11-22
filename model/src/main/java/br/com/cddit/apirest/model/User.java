package br.com.cddit.apirest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import br.com.cddit.apirest.model.validation.enums.UserValidationMessages;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1984599311745995697L;

	@Column(unique = true)
	@Size(min = 7, max = 50, message = UserValidationMessages.USERNAME_SIZE_MIN_MAX)
	protected String username;

	@Column(nullable = false)
	@Size(max = 50, message = UserValidationMessages.PASSWORD_SIZE_MAX)
	protected String password;

}
