package com.dam.springboot.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.*;

/**
 * Clase PotentialClient
 * 
 * Entidad que representa un cliente potencial de nuestra aplicación.
 * Contiene los atributos y etiquetas necesarios para la creación de la tabla usuarios
 * y sus relaciones con el resto de tablas. En concreto, Cuentas.
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Account
 */
@Entity
@Table(name = "usuarios")
public class PotentialClient implements Serializable {

	/**
	 * Id único para el objeto en la serialización
	 * Usamos la segunda opción para no darle el mismo a todas las clases
	 * y ganar ventaja de carga usando el generated y no el default
	 */
	private static final long serialVersionUID = -6553859171433372664L;

	/**
	 * Clave primaria
	 * 
	 * Número autoincremental
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	
	/**
	 * Número de identificación fiscal del cliente
	 * 
	 * Tiene que ser único y no puede quedarse vacío
	 */
	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "NIF", unique=true)
	@Getter @Setter private String nif;
	
	/**
	 * Apellidos del cliente potencial
	 */
	@NotEmpty(message = "no puede estar vacio")
	@Size(min = 4, max = 48, message = "el tamaño tiene que estar entre 4 y 48")
	@Column(name = "Apellidos")
	@Getter @Setter private String surname;
	
	/**
	 * Nombre del cliente potencial
	 */
	@NotEmpty(message = "no puede estar vacio")
	@Size(min = 2, max = 24, message = "el tamaño tiene que estar entre 2 y 24")
	@Column(name = "Nombre", nullable = false)
	@Getter @Setter private String name;	
	
	/**
	 * Año de nacimiento del cliente potencial
	 * 
	 * Opcional
	 */
	@Column(name = "AnyoNacimiento")
	@Getter @Setter private Integer yearBirth;	
	
	/**
	 * Dirección del cliente potencial
	 * 
	 * Opcional
	 */
	@Column(name = "Direccion")
	@Getter @Setter private String address;
	
	/**
	 * Email del cliente potencial
	 */
	@NotEmpty(message = "no puede estar vacio")
	@Email(message = "no es una dirección de correo bien formada")
	@Column(nullable = false)
	@Getter @Setter private String email;
	
	/**
	 * Teléfono del cliente potencial
	 */
	@NotEmpty(message = "no puede estar vacio")
	@Size(min = 9, max = 18, message = "el tamaño tiene que estar entre 9 y 18")
	@Column(nullable = false)
	@Getter @Setter private String tphno;
	
	/**
	 * Lista de cuentas bancarias del cliente
	 * 
	 * Relación N:M con Account
	 */
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "myOwners")
	@Getter @Setter private Set<Account> myAccounts;
	
	/**
	 * Impresión personalizada de PotentialClient para pruebas y verificaciones por consola
	 */
	@Override
	public String toString() {
		return "PotentialClient [id=" + id + ", nif=" + nif + ", surname=" + surname + ", name=" + name + ", yearBirth="
				+ yearBirth + ", address=" + address + ", email=" + email + ", tphno=" + tphno + "]";
	}
	
}
