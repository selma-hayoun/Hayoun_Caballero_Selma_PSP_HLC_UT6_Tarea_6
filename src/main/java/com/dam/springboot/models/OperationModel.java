package com.dam.springboot.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase OperationModel
 * 
 * Modelo para la recogida de datos para el mapeo de un objeto Operation,
 * para la creación y ejecución de operaciones
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Operation
 *
 */
public class OperationModel {
	/**
	 * Clave primaria
	 * 
	 * Número autoincremental
	 */
	@Getter @Setter private Long id;
	
	/**
	 * Montante de la operación
	 */
	@Getter @Setter private String amount;
	
	/**
	 * Clave foránea: Id de la cuenta bancaria a la cual pertenece
	 */
	@Getter @Setter private Long accountId;
	
	/**
	 * Atributo para recoger el ID de la cuenta destino en las operaciones de transferencia
	 */
	@Getter @Setter private Long accountIdTo;

	/**
	 * Impresión de los atributos del objeto por consola
	 */
	@Override
	public String toString() {
		return "OperationModel [id=" + id + ", amount=" + amount + ", accountId=" + accountId + ", accountIdTo="
				+ accountIdTo + "]";
	}

}
