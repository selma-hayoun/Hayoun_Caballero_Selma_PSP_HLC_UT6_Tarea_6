package com.dam.springboot.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "operaciones")
public class Operation implements Serializable {	

	private static final long serialVersionUID = -7284202363621680027L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Codigo")
	private Long id;
	
	@Column(name = "Operacion")
	private OperationType op;
	
	@NotNull(message = "no puede estar vacio")
	@Column(name = "FechaOperacion")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@NotNull(message = "no puede estar vacio")
	@Column(name = "Cantidad")
	private double amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Account acc;//Contiene al cliente que hace la operación
	
}
