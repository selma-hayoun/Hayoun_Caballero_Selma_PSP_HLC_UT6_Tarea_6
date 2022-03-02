package com.dam.springboot.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dam.springboot.entities.Account;
import com.dam.springboot.services.AccountServiceI;
import com.dam.springboot.services.AccountServiceImpl;

/**
 * Clase AccountRepository
 * 
 * Repositorio de nuestra tabla Cuentas. Nos permite dise&ntilde;ar m&eacute;todos personalizados de trabajo
 * en esta tabla y la tabla relacional N:M client_account 
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Account
 * @see AccountServiceI
 * @see AccountServiceImpl
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	/**
	 * M&eacute;todo para extraer un objeto Account seg&uacute;n un n&uacute;mero de cuenta dado
	 * 
	 * @param numAccount Cadena de caracteres n&uacute;mero de cuenta &uacute;nico
	 * @return Objeto Account cuyo su n&uacute;mero de cuenta es el enviado como par&aacute;metro
	 */
	Account getByNumAccount(String numAccount);
	
	/**
	 * M&eacute;todo para extraer un listado de cuentas bancarias por n&uacute;mero de cuenta
	 * 
	 * Devuelve todas las cuentas bancarias del sistema cuya n&uacute;mero de cuenta contiene la cadena
	 * de caract&eacute;res aportada por par&aacute;metro.
	 * 
	 * @param numAccount Cadena de caracteres con n&uacute;mero de cuenta &uacute;nico aproximado
	 * @return Objeto Account cuyo su n&uacute;mero de cuenta contiene el enviado como par&aacute;metro
	 */
	List<Account> findByNumAccountContaining(String numAccount);
		
	/**
	 * M&eacute;todo para inserta registros en la tabla relacional N:M client_account
	 * 
	 * Permite registrar las tuplas de relaci&oacute;n entre PotentialClient y Account cuando se da
	 * de alta o modifican los datos de una cuenta bancaria
	 * 
	 * @param idClient Id del cliente due&ntilde;o de la cuenta
	 * @param idAccount Id de la cuenta bancaria
	 */
	@Modifying
	@Query(value = "INSERT INTO client_account(potentialclient_id, account_id) VALUES ( :idClient, :idAccount)",
	nativeQuery = true)
	@Transactional
	void addClientAccountReg(@Param("idClient") Long idClient, @Param("idAccount") Long idAccount);
	
	/**
	 * M&eacute;todo para listar los ID de los clientes due&ntilde;os de una cuenta bancaria espec&iacute;fica
	 * 
	 * Permite sacar el listado de identificadores &uacute;nico de los PotentialClients vinculados a
	 * un ID concreto de cuenta bancaria.
	 * 
	 * @param id ID de la cuenta bancaria
	 * @return Listado de IDs (Long) de los clientes due&ntilde;os de la cuenta proporcionada como par&aacute;metro
	 */
	@Query(value = "SELECT a.potentialclient_id FROM client_account a WHERE a.account_id = :id",
			nativeQuery = true)
	List<Long> findPotentialClientsIdById(@Param("id") Long id);
	
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
	@Query(value = "SELECT * FROM cuentas c WHERE c.id NOT IN(:idList)",
			nativeQuery = true)
	List<Account> findAccountsIdNotOwnedById(@Param("idList") List<Long> idList);
	
	/**
	 * M&eacute;todo para borrar registros en la tabla N:M seg&uacute;n un ID de cuenta bancaria
	 * 
	 * @param id Identificador &uacute;nico de la cuenta bancaria cuyos registros se quieren eliminar
	 */
	@Modifying
	@Query(value = "DELETE FROM client_account WHERE account_id = :id",
	nativeQuery = true)
	@Transactional
	void deleteClientAccountReg(@Param("id") Long id);
	
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
	@Query(value = "SELECT COUNT(*) FROM client_account a WHERE a.account_id = :id",
			nativeQuery = true)
	Integer countOrphanAccounts(@Param("id") Long id);
	 	
}
