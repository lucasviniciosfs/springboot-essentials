package com.lucas.springbootessentials.model;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Student extends ID {

    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    
}
