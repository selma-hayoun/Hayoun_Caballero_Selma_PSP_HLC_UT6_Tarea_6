package com.dam.springboot.entities;

import java.io.Serializable;
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

import lombok.*;

@Entity
@Table(name = "usuarios")
public class PotentialClient implements Serializable {

	/**
	 * Id único para el objeto en la serialización
	 * Usamos la segunda opción para no darle el mismo a todas las clases
	 * y ganar ventaja de carga usando el generated y no el default
	 */
	private static final long serialVersionUID = -6553859171433372664L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	
	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "NIF", unique=true)
	@Getter @Setter private String nif;
	
	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "Apellidos")
	@Getter @Setter private String surname;
	
	@NotEmpty(message = "no puede estar vacio")
	@Size(min = 4, max = 24, message = "el tamaño tiene que estar entre 4 y 24")
	@Column(name = "Nombre", nullable = false)
	@Getter @Setter private String name;	
	
	@Column(name = "AnyoNacimiento")
	@Getter @Setter private Integer yearBirth;	
	
	@Column(name = "Direccion")
	@Getter @Setter private String address;
	
	@NotEmpty(message = "no puede estar vacio")
	@Email(message = "no es una dirección de correo bien formada")
	@Column(nullable = false, unique = true)
	@Getter @Setter private String email;
	
	@NotEmpty(message = "no puede estar vacio")
	@Size(min = 9, max = 18, message = "el tamaño tiene que estar entre 9 y 18")
	@Column(nullable = false)
	@Getter @Setter private String tphno;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "client_account",
    	joinColumns = @JoinColumn(name = "potentialclient_id"),
    	inverseJoinColumns = @JoinColumn(name = "account_id"))
	@Getter @Setter private Set<Account> myAccounts;

	@Override
	public String toString() {
		return "PotentialClient [id=" + id + ", nif=" + nif + ", surname=" + surname + ", name=" + name + ", yearBirth="
				+ yearBirth + ", address=" + address + ", email=" + email + ", tphno=" + tphno + "]";
	}
	
	
	
}
