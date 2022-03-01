package com.dam.springboot.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dam.springboot.entities.Account;

/**
 * Clase AccountRepository
 * 
 * Repositorio de nuestra tabla Cuentas. Nos permite diseñar métodos personalizados de trabajo
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
	 * Método para extraer un objeto Account según un número de cuenta dado
	 * 
	 * @param numAccount Cadena de caracteres número de cuenta único
	 * @return Objeto Account cuyo su número de cuenta es el enviado como parámetro
	 */
	Account getByNumAccount(String numAccount);
	
	/**
	 * Método para extraer un listado de cuentas bancarias por número de cuenta
	 * 
	 * Devuelve todas las cuentas bancarias del sistema cuya número de cuenta contiene la cadena
	 * de caractéres aportada por parámetro.
	 * 
	 * @param numAccount Cadena de caracteres con número de cuenta único aproximado
	 * @return Objeto Account cuyo su número de cuenta contiene el enviado como parámetro
	 */
	List<Account> findByNumAccountContaining(String numAccount);
		
	/**
	 * Método para inserta registros en la tabla relacional N:M client_account
	 * 
	 * Permite registrar las tuplas de relación entre PotentialClient y Account cuando se da
	 * de alta o modifican los datos de una cuenta bancaria
	 * 
	 * @param idClient Id del cliente dueño de la cuenta
	 * @param idAccount Id de la cuenta bancaria
	 */
	@Modifying
	@Query(value = "INSERT INTO client_account(potentialclient_id, account_id) VALUES ( :idClient, :idAccount)",
	nativeQuery = true)
	@Transactional
	void addClientAccountReg(@Param("idClient") Long idClient, @Param("idAccount") Long idAccount);
	
	/**
	 * Método para listar los ID de los clientes dueños de una cuenta bancaria específica
	 * 
	 * Permite sacar el listado de identificadores único de los PotentialClients vinculados a
	 * un ID concreto de cuenta bancaria.
	 * 
	 * @param id ID de la cuenta bancaria
	 * @return Listado de IDs (Long) de los clientes dueños de la cuenta proporcionada como parámetro
	 */
	@Query(value = "SELECT a.potentialclient_id FROM client_account a WHERE a.account_id = :id",
			nativeQuery = true)
	List<Long> findPotentialClientsIdById(@Param("id") Long id);
	
	/**
	 * Método para listar las cuentas bancarias que no pertenecen a un listado de ID de clientes datos
	 * 
	 * Permite sacar un listado de objetos Account que no son pertenencientes al conjunto de IDs de clientes
	 * proporcionados como parámetro. Se utilizó en iteraciones intermedias del programa para la implementación
	 * de las vistas de actualización de la cuenta bancaria para limitar la información a visualizar.
	 * 
	 * @param Listado de IDs pertenecientes a un conjunto de clientes
	 * @return Listado de cuentas bancarias que no son propiedad del listado de IDs proporcionados por parámetro
	 */
	@Query(value = "SELECT * FROM cuentas c WHERE c.id NOT IN(:idList)",
			nativeQuery = true)
	List<Account> findAccountsIdNotOwnedById(@Param("idList") List<Long> idList);
	
	/**
	 * Método para borrar registros en la tabla N:M según un ID de cuenta bancaria
	 * 
	 * @param id Identificador único de la cuenta bancaria cuyos registros se quieren eliminar
	 */
	@Modifying
	@Query(value = "DELETE FROM client_account WHERE account_id = :id",
	nativeQuery = true)
	@Transactional
	void deleteClientAccountReg(@Param("id") Long id);
	
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
	@Query(value = "SELECT COUNT(*) FROM client_account a WHERE a.account_id = :id",
			nativeQuery = true)
	Integer countOrphanAccounts(@Param("id") Long id);
	 	
}
