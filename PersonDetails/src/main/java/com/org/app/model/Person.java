package com.org.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONS")
public class Person implements Serializable {

	private static final long serialVersionUID = -8712872385957386182L;

	private Long id = null;
	private String firstName = null;
	private String lastName = null;

	/**
	 * Gets id (primary key).
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	/**
	 * Sets id (primary key).
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
