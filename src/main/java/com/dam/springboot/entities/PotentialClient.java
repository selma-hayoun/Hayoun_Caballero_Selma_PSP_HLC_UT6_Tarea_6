package com.dam.springboot.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "NIF", unique=true)
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
	@JoinTable(name = "client_account",
    	joinColumns = @JoinColumn(name = "potentialclient_id"),
    	inverseJoinColumns = @JoinColumn(name = "account_id"))
	private List<Account> myAccounts;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Account> getMyAccounts() {
		return myAccounts;
	}

	public void setMyAccounts(List<Account> myAccounts) {
		this.myAccounts = myAccounts;
	}


	
	
}
