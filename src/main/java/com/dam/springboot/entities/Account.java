package com.dam.springboot.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Table(name = "Cuentas")
public class Account implements Serializable {

	/**
	 * Id único para el objeto en la serialización
	 * Usamos la segunda opción para no darle el mismo a todas las clases
	 * y ganar ventaja de carga usando el generated y no el default
	 */
	private static final long serialVersionUID = 8720110895955302507L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	
	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "NumeroCuenta", unique=true)
	@Getter @Setter private String numAccount;
	
	@NotNull(message = "no puede estar vacio")
	@Column(name = "FechaAlta")
	@Temporal(TemporalType.DATE)
	@Getter @Setter private Date createAt;
	
	@NotEmpty(message = "no puede estar vacio")	
	@Column(name = "Saldo", nullable = false)
	@Getter @Setter private double balance = 0;//Inicio en 0
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "myAccounts")
	@Getter @Setter private Set<PotentialClient> myOwners;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	@Getter @Setter private List<Operation> operations;

	@Override
	public String toString() {
		return "Account [id=" + id + ", numAccount=" + numAccount + ", createAt=" + createAt + ", balance=" + balance
				+ "]";
	}	
	
	

}
