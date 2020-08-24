package com.org.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADDRESS")
public class Address implements Serializable {

	private static final long serialVersionUID = 7851794269407495684L;

	private Long id = null;
	private String city = null;
	private String state = null;
	private String postalCode = null;
	private String street = null;
	private Long personId;

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
	 * Gets city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets city.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets state.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets zip or postal code.
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets zip or postal code.
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Gets country.
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets country.
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

}