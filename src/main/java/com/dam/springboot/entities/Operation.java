package com.dam.springboot.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Table(name = "operaciones")
public class Operation implements Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	
	@Column(name = "Operacion")
	@Enumerated(value = EnumType.STRING)
	@Getter @Setter private OperationType op;
	
	@NotNull(message = "no puede estar vacio")
	@Column(name = "FechaOperacion")
	@Temporal(TemporalType.DATE)
	@Getter @Setter private Date createAt;
	
	@NotNull(message = "no puede estar vacio")
	@Column(name = "Cantidad")
	@Getter @Setter private double amount;

	@Override
	public String toString() {
		return "Operation [id=" + id + ", op=" + op + ", createAt=" + createAt + ", amount=" + amount + "]";
	}
	
	
	
}
