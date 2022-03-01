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

/**
 * Clase Operation
 * 
 * Entidad que representa una operación de nuestra aplicación.
 * Contiene los atributos y etiquetas necesarios para la creación de la tabla Operaciones
 * y sus relaciones con el resto de tablas. En concreto, Cuentas.
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Account
 */
@Entity
@Table(name = "operaciones")
public class Operation implements Serializable {	

	/**
	 * Id único para el objeto en la serialización
	 * Usamos la segunda opción para no darle el mismo a todas las clases
	 * y ganar ventaja de carga usando el generated y no el default
	 */
	private static final long serialVersionUID = -7564460337320474981L;

	/**
	 * Clave primaria
	 * 
	 * Número autoincremental
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	
	/**
	 * Tipo de operación
	 * 
	 * Recoge un valor de nuestro enumerado en formato texto gracias a la etiqueta @Enumerated(value = EnumType.STRING)
	 */
	@Column(name = "Operacion")
	@Enumerated(value = EnumType.STRING)
	@Getter @Setter private OperationType op;
	
	/**
	 * Fecha de realización de la operación
	 */
	@NotNull(message = "no puede estar vacio")
	@Column(name = "FechaOperacion")
	@Temporal(TemporalType.DATE)
	@Getter @Setter private Date createAt;
	
	/**
	 * Montante de la operación
	 * 
	 * Por defecto se inicializa a 0
	 */
	@NotNull(message = "no puede estar vacio")
	@Column(name = "Cantidad")
	@Getter @Setter private double amount = 0;
	
	/**
	 * Clave foránea: Id de la cuenta bancaria a la cual pertenece
	 * 
	 * La relación 1:N entre Account y Operation ya establece esta clave foránea automáticamente,
	 * pero hemos mapeado el atributo para poder trabajar con las operaciones desde el servicio.
	 */
	@Getter @Setter private Long account_id;

	/**
	 * Impresión personalizada de Operation para pruebas y verificaciones por consola
	 */
	@Override
	public String toString() {
		return "Operation [id=" + id + ", op=" + op + ", createAt=" + createAt + ", amount=" + amount + "]";
	}
	
}
