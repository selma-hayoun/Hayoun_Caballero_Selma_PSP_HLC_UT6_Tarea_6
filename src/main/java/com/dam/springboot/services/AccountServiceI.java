package com.dam.springboot.services;

import java.util.List;
import com.dam.springboot.entities.Account;
import com.dam.springboot.repositories.AccountRepository;


/**
 * Clase AccountServiceI: Interfaz del servicio para tabla Cuentas
 * 
 * Interfaz que recoge todos los m&eacute;todos de CRUD para nuestra tabla cuentas,
 * as&iacute; como otros necesarios para las operaciones relacionales de la tabla 
 * N:M client_account.
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Account
 * @see AccountRepository
 * @see AccountServiceImpl
 *
 */
public interface AccountServiceI {
	
	/**
	 * M&eacute;todo para listar todas las cuentas bancarias
	 * 
	 * @return Listado con todos los objetos Account de la tabla Cuentas
	 */
	public List<Account> findAllAccount();
	
	/**
	 * M&eacute;todo para extraer un objeto Account seg&uacute;n un n&uacute;mero de cuenta dado
	 * 
	 * @param numAccount Cadena de caracteres n&uacute;mero de cuenta &uacute;nico
	 * @return Objeto Account cuyo su n&uacute;mero de cuenta es el enviado como par&aacute;metro
	 */
	public Account getAccountByNumAccount(String numAccount);
	
	/**
	 * M&eacute;todo para extraer un listado de cuentas bancarias por n&uacute;mero de cuenta
	 * 
	 * Devuelve todas las cuentas bancarias del sistema cuya n&uacute;mero de cuenta contiene la cadena
	 * de caract&eacute;res aportada por par&aacute;metro.
	 * 
	 * @param numAccount Cadena de caracteres con n&uacute;mero de cuenta &uacute;nico aproximado
	 * @return Objeto Account cuyo su n&uacute;mero de cuenta contiene el enviado como par&aacute;metro
	 */
	public List<Account> findByNumAccountContaining(String numAccount);
	
	/**
	 * M&eacute;todo para extraer un objeto Account por su ID
	 * 
	 * @param id Long identificador &uacute;nico de la cuenta
	 * @return Objeto Account (Cuenta bancaria) cuyo ID es el proporcionado como par&aacute;metro
	 */
	public Account getById(Long id);
	
	/**
	 * M&eacute;todo para a&ntilde;adir una cuenta bancaria al sistema
	 * 
	 * @param acc Objeto Account que se quiere insertar en la tabla Cuentas
	 */
	public void addAccount(Account acc);	
	
	/**
	 * M&eacute;todo para eliminar una cuenta bancaria por su ID
	 * 
	 * @param IdAccount Long identificador &uacute;nico de la cuenta
	 */
	public void removeAccountById(long IdAccount);
	
	/**
	 * M&eacute;todo para actualizar los datos de una cuenta del sistema
	 * 
	 * @param acc Objeto Account con los datos actualizados que se desea mergear
	 */
	public void updateAccount(Account acc);	
	
	/**
	 * M&eacute;todo para extraer un listado de cuentas del sistema seg&uacute;n un listado de IDs
	 * 
	 * @param accIds Listado de Long, identificadores &uacute;nicos de la cuentas
	 * @return Listado de objetos Account cuyos IDs se encuentran en el listado proporcionado como par&aacute;metro
	 */
	public List<Account> findAccountsById(List<Long> accIds);
	
	/**
	 * M&eacute;todo para inserta registros en la tabla relacional N:M client_account
	 * 
	 * Permite registrar las tuplas de relaci&oacute;n entre PotentialClient y Account cuando se da
	 * de alta o modifican los datos de una cuenta bancaria
	 * 
	 * @param idClient Id del cliente due&ntilde;o de la cuenta
	 * @param idAccount Id de la cuenta bancaria
	 */
	public void addClientAccountReg(Long idClient, Long idAccount);
	
	/**
	 * M&eacute;todo para borrar registros en la tabla N:M seg&uacute;n un ID de cuenta bancaria
	 * 
	 * @param id Identificador &uacute;nico de la cuenta bancaria cuyos registros se quieren eliminar
	 */
	public void deleteClientAccountReg(Long id);
	
	/**
	 * M&eacute;todo para listar los ID de los clientes due&ntilde;os de una cuenta bancaria espec&iacute;fica
	 * 
	 * Permite sacar el listado de identificadores &uacute;nico de los PotentialClients vinculados a
	 * un ID concreto de cuenta bancaria.
	 * 
	 * @param id ID de la cuenta bancaria
	 * @return Listado de IDs (Long) de los clientes due&ntilde;os de la cuenta proporcionada como par&aacute;metro
	 */
	public List<Long> findPotentialClientsIdById(Long id);
	
	/**
	 * M&eacute;todo para localizar las cuentas h&uacute;erfanas en nuestra tabla N:M client_account
	 * 
	 * Tras la eliminaci&oacute;n de un cliente y sus registros en la tabla relacional N:M,
	 * podr&iacute;an existir cuentas de las cuales &eacute;l fuera el &uacute;nico due&ntilde;o. Estas cuentas existir&aacute;n 
	 * en nuestra tabla Cuentas pero no en client_account. Debemos verificarlo para proceder a 
	 * la eliminaci&oacute;n manual posterior de la cuenta hu&eacute;rfana o sin due&ntilde;os.
	 * 
	 * @param id Identificador &uacute;nico de la cuenta bancaria a verificar
	 * @return Devuelve el n&uacute;mero de registros que existen en la tabla client_account con referencia al ID proporcionado
	 */
	public Integer countOrphanAccounts(Long id);
	
	/**
	 * M&eacute;todo para listar las cuentas bancarias que no pertenecen a un listado de ID de clientes datos
	 * 
	 * Permite sacar un listado de objetos Account que no son pertenencientes al conjunto de IDs de clientes
	 * proporcionados como par&aacute;metro. Se utiliz&oacute; en iteraciones intermedias del programa para la implementaci&oacute;n
	 * de las vistas de actualizaci&oacute;n de la cuenta bancaria para limitar la informaci&oacute;n a visualizar.
	 * 
	 * @param idList Listado de IDs pertenecientes a un conjunto de clientes
	 * @return Listado de cuentas bancarias que no son propiedad del listado de IDs proporcionados por par&aacute;metro
	 */
	public List<Account> findAccountsIdNotOwnedById(List<Long> idList);

}
