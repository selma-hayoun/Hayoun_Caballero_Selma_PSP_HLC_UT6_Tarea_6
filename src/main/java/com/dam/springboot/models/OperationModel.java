package com.dam.springboot.models;

import com.dam.springboot.entities.Operation;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase OperationModel
 * 
 * Modelo para la recogida de datos para el mapeo de un objeto Operation,
 * para la creaci&oacute;n y ejecuci&oacute;n de operaciones
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
	 * N&uacute;mero autoincremental
	 */
	@Getter @Setter private Long id;
	
	/**
	 * Montante de la operaci&oacute;n
	 */
	@Getter @Setter private String amount;
	
	/**
	 * Clave for&aacute;nea: Id de la cuenta bancaria a la cual pertenece
	 */
	@Getter @Setter private Long accountId;
	
	/**
	 * Atributo para recoger el ID de la cuenta destino en las operaciones de transferencia
	 */
	@Getter @Setter private Long accountIdTo;

	/**
	 * Impresi&oacute;n de los atributos del objeto por consola
	 */
	@Override
	public String toString() {
		return "OperationModel [id=" + id + ", amount=" + amount + ", accountId=" + accountId + ", accountIdTo="
				+ accountIdTo + "]";
	}

}
