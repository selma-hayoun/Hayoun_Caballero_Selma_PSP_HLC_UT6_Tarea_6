package com.dam.springboot.models;

import com.dam.springboot.entities.Account;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase AccountModel
 * 
 * Modelo para la recogida de datos para el mapeo de un objeto Account, tanto en su creación
 * como para su actualización.
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Account
 *
 */
public class AccountModel {
	
	/**
	 * Clave primaria
	 * 
	 * Número autoincremental
	 */
	@Getter @Setter private Long id;
	
	/**
	 * Número de cuenta bancaria
	 * 
	 * Tiene que ser único y no puede quedarse vacío
	 */
	@Getter @Setter private String numAccount;
	
	/**
	 * Saldo de la cuenta bancaria
	 */
	@Getter @Setter private String balance;
	
	/**
	 * Array de identificadores de dueños recuperados de la vista
	 */
	@Getter @Setter private Long[] myOwners;

	/**
	 * Impresión de los atributos del objeto por consola
	 */
	@Override
	public String toString() {
		return "AccountModel [id=" + id + ", numAccount=" + numAccount + ", balance="
				+ balance + "]";
	}
	
}
