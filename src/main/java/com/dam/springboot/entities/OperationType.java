package com.dam.springboot.entities;

/**
 * Enumerado con los tipos de operaciones posibles en nuestra aplicaci&oacute;n
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 *
 */
public enum OperationType {
	/**
	 * Dep&oacute;sito en una cuenta bancaria
	 */
	DEPOSIT,
	/**
	 * Retirada de una cuenta bancaria
	 */
	WITHDRAWAL,
	/**
	 * Salida de dinero de una cuenta bancaria para su transferencia
	 */
	ISSUED_TRANSFER,
	/**
	 * Entrada de dinero en una cuenta por una transferencia
	 */
	RECEIVED_TRANSFER
}
