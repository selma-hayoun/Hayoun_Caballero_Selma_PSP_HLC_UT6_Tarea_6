package com.dam.springboot.models;

import com.dam.springboot.entities.Account;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase AccountModel
 * 
 * Modelo para la recogida de datos para el mapeo de un objeto Account, tanto en su creaci&oacute;n
 * como para su actualizaci&oacute;n.
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
	 * N&uacute;mero autoincremental
	 */
	@Getter @Setter private Long id;
	
	/**
	 * N&uacute;mero de cuenta bancaria
	 * 
	 * Tiene que ser &uacute;nico y no puede quedarse vac&iacute;o
	 */
	@Getter @Setter private String numAccount;
	
	/**
	 * Saldo de la cuenta bancaria
	 */
	@Getter @Setter private String balance;
	
	/**
	 * Array de identificadores de due&ntilde;os recuperados de la vista
	 */
	@Getter @Setter private Long[] myOwners;

	/**
	 * Impresi&oacute;n de los atributos del objeto por consola
	 */
	@Override
	public String toString() {
		return "AccountModel [id=" + id + ", numAccount=" + numAccount + ", balance="
				+ balance + "]";
	}
	
}
