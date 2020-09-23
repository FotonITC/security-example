package com.sysconsult.exportplanning.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity(name = "users")
public class User {

	public User() {

	}

	public User(String firstName, String lastName, String email, String password, List<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String firstName;
	@NotNull
	private String lastName;

	@Column(unique = true)
	@Email
	@NotNull
	private String email;

	@NotNull
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;

}