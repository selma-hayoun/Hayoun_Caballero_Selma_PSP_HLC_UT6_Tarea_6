package com.dam.springboot.services;

import java.util.List;
import com.dam.springboot.entities.PotentialClient;
import com.dam.springboot.repositories.PotentialClientRepository;

/**
 * Clase PotentialClientServiceI: Interfaz del servicio para tabla Usuarios
 * 
 * Interfaz que recoge todos los métodos de CRUD para nuestra tabla usuarios,
 * así como otros necesarios para las operaciones relacionales de la tabla 
 * N:M client_account.
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see PotentialClient
 * @see PotentialClientRepository
 * @see PotentialClientServiceImpl
 *
 */
public interface PotentialClientServiceI {

	/**
	 * Método para extraer todos los clientes potenciales del sistema
	 * 
	 * @return Listado de objetos PotentialClient
	 */
	public List<PotentialClient> findAllPotentialClient();
	
	/**
	 * Método para extraer un cliente potencial por su NIF(exacto)
	 * 
	 * @param nif Atributo nif del PotentialClient
	 * @return Objeto PotentialClient de la tabla Usuarios cuyo nif coincide con el porporcionado por parámetro
	 */
	public PotentialClient getPotentialClientByNif(String nif);
	
	/**
	 * Método para extraer un cliente potencial por su ID
	 * 
	 * @param id Long identificador del cliente potencial
	 * @return Objeto PotentialClient cuyo ID es el proporcionado como parámetro
	 */
	public PotentialClient getById(Long id);
	
	/**
	 * Método para extraer un listado de clientes potenciales por contener la cadena suministrada
	 * 
	 * @param nif Cadena de texto parcial o total del nif del PotentialClient
	 * @return Listado de objetos PotentialClient cuyo nif contiene el parámetro suministrado como parámetro
	 */
	public List<PotentialClient> findPotentialClientByNifContaining(String nif);
	
	/**
	 * Método para extraer un listado de clientes potenciales por contener la cadena suministrada
	 * 
	 * @param name Cadena de texto parcial o total del nombre del PotentialClient
	 * @return Listado de objetos PotentialClient cuyo name contiene el parámetro suministrado como parámetro
	 */
	public List<PotentialClient> findPotentialClientByNameContaining(String name);
	
	/**
	 * Método para añadir un cliente potencial a la tabla Usuarios
	 * 
	 * @param c Objeto PotentialClient que se desea insertar
	 */
	public void addPotentialClient(PotentialClient c);	
	
	/**
	 * Método para eliminar un cliente potencial por su ID
	 * 
	 * @param IdPotentialClient Long identificador del PotentialClient a eliminar
	 */
	public void removePotentialClientById(long IdPotentialClient);
	
	/**
	 * Método para actualizar un cliente potencial del sistema
	 * 
	 * @param c Objeto PotentialClient con los datos actualizados
	 */
	public void updatePotentialClient(PotentialClient c);	
	
	/**
	 * Método para extraer el listado de ID de las cuentas bancarias que posee el 
	 * cliente potencial cuyo ID se pasa como parámetro
	 * 
	 * @param id Long identificador del PotentialClient
	 * @return Listado de Long, identificadores de las cuentas bancarias de las cuales es titular
	 */
	public List<Long> findAccountsIdById(Long id);
	
	/**
	 * Método para extraer un listad de clientes potenciales por sus IDs
	 * 
	 * Puede utilizarse para consultas encedenadas o dependientes, de cuentas o la
	 * tabla N:M account_client
	 * 
	 * @param cIds Listado Longs de identificadores de clientes potenciales
	 * @return Listado de objetos PotentialClient cuyos ID se encuentran dentro del listado proporcionado como parámetro
	 */
	public List<PotentialClient> findPotentialClientsById(List<Long> cIds);
	
	/**
	 * Método para eliminar registros de clientes potenciales de la tabla client_account
	 * 
	 * @param id Long identificador único del cliente potencial cuyos regitros se desean eliminar
	 */
	public void deleteClientAccountReg(Long id);
	
	/**
	 * Método para extraer los ID de los PotentialClient que poseen alguna cuenta bancaria
	 * 
	 * @return Listado Long de identificadores de clientes potenciales
	 */
	public List<Long> findPotentialClientsOwnersId();
	
}
