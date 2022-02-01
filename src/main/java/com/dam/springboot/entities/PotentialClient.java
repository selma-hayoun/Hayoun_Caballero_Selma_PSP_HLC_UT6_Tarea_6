package com.dam.springboot.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "usuarios")
public class PotentialClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String nif;
	
	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "Apellidos")
	private String surname;
	
	@NotEmpty(message = "no puede estar vacio")
	@Size(min = 4, max = 24, message = "el tamaño tiene que estar entre 4 y 24")
	@Column(name = "Nombre", nullable = false)
	private String name;	
	
	@Column(name = "AnyoNacimiento")
	private int yearBirth;	
	
	@Column(name = "Direccion")
	private String address;
	
	@NotEmpty(message = "no puede estar vacio")
	@Email(message = "no es una dirección de correo bien formada")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotEmpty(message = "no puede estar vacio")
	@Column(nullable = false)
	private int tphno;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Account> myAccounts;

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearBirth() {
		return yearBirth;
	}

	public void setYearBirth(int yearBirth) {
		this.yearBirth = yearBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTphno() {
		return tphno;
	}

	public void setTphno(int tphno) {
		this.tphno = tphno;
	}

	public Set<Account> getMyAccounts() {
		return myAccounts;
	}

	public void setMyAccounts(Set<Account> myAccounts) {
		this.myAccounts = myAccounts;
	}
	
	
}
