package com.dam.springboot.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import lombok.*;

/**
 * Clase Account
 * 
 * Entidad que representa una cuenta bancaria de nuestra aplicaci&oacute;n.
 * Contiene los atributos y etiquetas necesarios para la creaci&oacute;n de la tabla Cuentas
 * y sus relaciones con el resto de tablas.
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see PotentialClient
 * @see Operation
 *
 */
@Entity
@Table(name = "Cuentas")
public class Account implements Serializable {

	/**
	 * Id &uacute;nico para el objeto en la serializaci&oacute;n
	 * Usamos la segunda opci&oacute;n para no darle el mismo a todas las clases
	 * y ganar ventaja de carga usando el generated y no el default
	 */
	private static final long serialVersionUID = 8720110895955302507L;

	/**
	 * Clave primaria
	 * 
	 * N&uacute;mero autoincremental
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	
	/**
	 * N&uacute;mero de cuenta bancaria
	 * 
	 * Tiene que ser &uacute;nico y no puede quedarse vac&iacute;o
	 */
	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "NumeroCuenta", unique=true)
	@Getter @Setter private String numAccount;
	
	/**
	 * Fecha de creaci&oacute;n de la cuenta bancaria
	 */
	@Column(name = "FechaAlta")
	@Temporal(TemporalType.DATE)
	@Getter @Setter private Date createAt;
	
	/**
	 * Saldo de la cuenta bancaria
	 * 
	 * Por defecto se inicializa a 0
	 */
	@Column(name = "Saldo", nullable = false)
	@Getter @Setter private double balance = 0;//Inicio en 0
	
	/**
	 * Lista de due&ntilde;os de la cuenta bancaria
	 * 
	 * Establece la relaci&oacute;n N:M entre Account y PotentialClient
	 * mediante la creaci&oacute;n de la tabla relacional client_account
	 * 
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "client_account",
	joinColumns = @JoinColumn(name = "account_id"),
	inverseJoinColumns = @JoinColumn(name = "potentialclient_id"))
	@Getter @Setter private Set<PotentialClient> myOwners;
	
	/**
	 * Lista de operaciones de la cuenta bancaria
	 * 
	 * Establece la relaci&oacute;n 1:N entre Account y Operation
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	@Getter @Setter private List<Operation> operations;
	
	/**
	 * Impresi&oacute;n personalizada de Account para pruebas y verificaciones por consola
	 */
	@Override
	public String toString() {
		return "Account [id=" + id + ", numAccount=" + numAccount + ", createAt=" + createAt + ", balance=" + balance
				+ "]";
	}	

}
