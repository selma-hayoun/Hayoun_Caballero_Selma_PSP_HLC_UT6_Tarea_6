package com.dam.springboot.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.dam.springboot.entities.PotentialClient;
import com.dam.springboot.services.PotentialClientServiceI;
import com.dam.springboot.services.PotentialClientServiceImpl;

/**
 * Clase PotentialClientRepository
 * 
 * Repositorio de nuestra tabla Usuarios. Nos permite diseñar métodos personalizados de trabajo
 * en esta tabla y la tabla relacional N:M client_account 
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see PotentialClient
 * @see PotentialClientServiceI
 * @see PotentialClientServiceImpl
 *
 */
@Repository
public interface PotentialClientRepository extends JpaRepository<PotentialClient, Long> {
	
	/**
	 * Método para extraer un cliente potencial por su NIF(exacto)
	 * 
	 * @param nif Atributo nif del PotentialClient
	 * @return Objeto PotentialClient de la tabla Usuarios cuyo nif coincide con el porporcionado por parámetro
	 */
	PotentialClient getByNif(String nif);
	
	/**
	 * Método para extraer un listado de clientes potenciales por contener la cadena suministrada
	 * 
	 * @param nif Cadena de texto parcial o total del nif del PotentialClient
	 * @return Listado de objetos PotentialClient cuyo nif contiene el parámetro suministrado como parámetro
	 */
	List<PotentialClient> findByNifContaining(String nif);
	
	/**
	 * Método para extraer un listado de clientes potenciales por contener la cadena suministrada
	 * 
	 * @param name Cadena de texto parcial o total del nombre del PotentialClient
	 * @return Listado de objetos PotentialClient cuyo name contiene el parámetro suministrado como parámetro
	 */
	List<PotentialClient> findByNameContaining(String name);	
	
	/**
	 * Método para extraer el listado de ID de las cuentas bancarias que posee el 
	 * cliente potencial cuyo ID se pasa como parámetro
	 * 
	 * @param id Long identificador del PotentialClient
	 * @return Listado de Long, identificadores de las cuentas bancarias de las cuales es titular
	 */
	@Query(value = "SELECT c.account_id FROM client_account c WHERE c.potentialclient_id = :id",
			nativeQuery = true)
	List<Long> findAccountsIdById(@Param("id") Long id);
	
	/**
	 * Método para extraer los ID de los PotentialClient que poseen alguna cuenta bancaria
	 * 
	 * @return Listado Long de identificadores de clientes potenciales
	 */
	@Query(value = "SELECT c.potentialclient_id FROM client_account c",
			nativeQuery = true)
	List<Long> findPotentialClientsOwnersId();
	
	/**
	 * Método para eliminar registros de clientes potenciales de la tabla client_account
	 * 
	 * @param id Long identificador único del cliente potencial cuyos regitros se desean eliminar
	 */
	@Modifying
	@Query(value = "DELETE FROM client_account WHERE potentialclient_id = :id",
	nativeQuery = true)
	@Transactional
	void deleteClientAccountReg(@Param("id") Long id);
	
}
