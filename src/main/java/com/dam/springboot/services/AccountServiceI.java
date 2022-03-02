package com.dam.springboot.services;

import java.util.List;
import com.dam.springboot.entities.Account;
import com.dam.springboot.repositories.AccountRepository;


/**
 * Clase AccountServiceI: Interfaz del servicio para tabla Cuentas
 * 
 * Interfaz que recoge todos los métodos de CRUD para nuestra tabla cuentas,
 * así como otros necesarios para las operaciones relacionales de la tabla 
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
	 * Método para listar todas las cuentas bancarias
	 * 
	 * @return Listado con todos los objetos Account de la tabla Cuentas
	 */
	public List<Account> findAllAccount();
	
	/**
	 * Método para extraer un objeto Account según un número de cuenta dado
	 * 
	 * @param numAccount Cadena de caracteres número de cuenta único
	 * @return Objeto Account cuyo su número de cuenta es el enviado como parámetro
	 */
	public Account getAccountByNumAccount(String numAccount);
	
	/**
	 * Método para extraer un listado de cuentas bancarias por número de cuenta
	 * 
	 * Devuelve todas las cuentas bancarias del sistema cuya número de cuenta contiene la cadena
	 * de caractéres aportada por parámetro.
	 * 
	 * @param numAccount Cadena de caracteres con número de cuenta único aproximado
	 * @return Objeto Account cuyo su número de cuenta contiene el enviado como parámetro
	 */
	public List<Account> findByNumAccountContaining(String numAccount);
	
	/**
	 * Método para extraer un objeto Account por su ID
	 * 
	 * @param id Long identificador único de la cuenta
	 * @return Objeto Account (Cuenta bancaria) cuyo ID es el proporcionado como parámetro
	 */
	public Account getById(Long id);
	
	/**
	 * Método para añadir una cuenta bancaria al sistema
	 * 
	 * @param acc Objeto Account que se quiere insertar en la tabla Cuentas
	 */
	public void addAccount(Account acc);	
	
	/**
	 * Método para eliminar una cuenta bancaria por su ID
	 * 
	 * @param IdAccount Long identificador único de la cuenta
	 */
	public void removeAccountById(long IdAccount);
	
	/**
	 * Método para actualizar los datos de una cuenta del sistema
	 * 
	 * @param acc Objeto Account con los datos actualizados que se desea mergear
	 */
	public void updateAccount(Account acc);	
	
	/**
	 * Método para extraer un listado de cuentas del sistema según un listado de IDs
	 * 
	 * @param accIds Listado de Long, identificadores únicos de la cuentas
	 * @return Listado de objetos Account cuyos IDs se encuentran en el listado proporcionado como parámetro
	 */
	public List<Account> findAccountsById(List<Long> accIds);
	
	/**
	 * Método para inserta registros en la tabla relacional N:M client_account
	 * 
	 * Permite registrar las tuplas de relación entre PotentialClient y Account cuando se da
	 * de alta o modifican los datos de una cuenta bancaria
	 * 
	 * @param idClient Id del cliente dueño de la cuenta
	 * @param idAccount Id de la cuenta bancaria
	 */
	public void addClientAccountReg(Long idClient, Long idAccount);
	
	/**
	 * Método para borrar registros en la tabla N:M según un ID de cuenta bancaria
	 * 
	 * @param id Identificador único de la cuenta bancaria cuyos registros se quieren eliminar
	 */
	public void deleteClientAccountReg(Long id);
	
	/**
	 * Método para listar los ID de los clientes dueños de una cuenta bancaria específica
	 * 
	 * Permite sacar el listado de identificadores único de los PotentialClients vinculados a
	 * un ID concreto de cuenta bancaria.
	 * 
	 * @param id ID de la cuenta bancaria
	 * @return Listado de IDs (Long) de los clientes dueños de la cuenta proporcionada como parámetro
	 */
	public List<Long> findPotentialClientsIdById(Long id);
	
	/**
	 * Método para localizar las cuentas húerfanas en nuestra tabla N:M client_account
	 * 
	 * Tras la eliminación de un cliente y sus registros en la tabla relacional N:M,
	 * podrían existir cuentas de las cuales él fuera el único dueño. Estas cuentas existirán 
	 * en nuestra tabla Cuentas pero no en client_account. Debemos verificarlo para proceder a 
	 * la eliminación manual posterior de la cuenta huérfana o sin dueños.
	 * 
	 * @param id Identificador único de la cuenta bancaria a verificar
	 * @return Devuelve el número de registros que existen en la tabla client_account con referencia al ID proporcionado
	 */
	public Integer countOrphanAccounts(Long id);
	
	/**
	 * Método para listar las cuentas bancarias que no pertenecen a un listado de ID de clientes datos
	 * 
	 * Permite sacar un listado de objetos Account que no son pertenencientes al conjunto de IDs de clientes
	 * proporcionados como parámetro. Se utilizó en iteraciones intermedias del programa para la implementación
	 * de las vistas de actualización de la cuenta bancaria para limitar la información a visualizar.
	 * 
	 * @param idList Listado de IDs pertenecientes a un conjunto de clientes
	 * @return Listado de cuentas bancarias que no son propiedad del listado de IDs proporcionados por parámetro
	 */
	public List<Account> findAccountsIdNotOwnedById(List<Long> idList);

}
