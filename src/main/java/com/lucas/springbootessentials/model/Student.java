package com.lucas.springbootessentials.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
public class Student extends ID {

	@NotEmpty(message = "Nome do aluno é obrigatório!")
	@NotNull(message = "Nome do aluno é obrigatório!")
    private String name;

	@NotEmpty(message = "Email do aluno é obrigatório!")
	@NotNull(message = "Email do aluno é obrigatório!")
	@Email
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
