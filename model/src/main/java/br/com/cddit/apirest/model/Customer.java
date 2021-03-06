package br.com.cddit.apirest.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//@Getter
//@Setter
//@NoArgsConstructor
//@EqualsAndHashCode(callSuper = true)
//@Entity
// @Table(name = "customers", uniqueConstraints = @UniqueConstraint(columnNames
// = "email", name = "customer_email_uk"))
//@Table(name = "customers")
// @PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "fk_customer_to_user"))
public class Customer extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8206631962934087282L;

	@NotNull
	@Column(unique = true)
	@Size(min = 7, max = 50)
	private String email;

	@Pattern(regexp = "[0-9]+", message = "The id must be a valid number")
	@Column
	@Size(min = 8, max = 15)
	private String phone;

	@Column
	@Size(min = 2, max = 50)
	private String department;

	@Column
	@Size(min = 2, max = 50)
	private String jobRole;
}
