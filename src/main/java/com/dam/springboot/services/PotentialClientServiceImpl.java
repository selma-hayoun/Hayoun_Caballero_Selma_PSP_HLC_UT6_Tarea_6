package com.dam.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dam.springboot.entities.PotentialClient;
import com.dam.springboot.repositories.PotentialClientRepository;

/**
 * Clase PotentialClientServiceImpl: Implementa los m&eacute;todos de la interfaz de servicio PotentialClientServiceI
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see PotentialClient
 * @see PotentialClientServiceI
 *
 */
@Service
public class PotentialClientServiceImpl implements PotentialClientServiceI{

	/**
	 * Inyecci&oacute;n de dependencias: Repositorio de la tabla Usuarios
	 */
	@Autowired
	private PotentialClientRepository potentialClientRepository;	
	
	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer todos los clientes potenciales del sistema
	 * 
	 * @return Listado de objetos PotentialClient
	 */
	@Override
	public List<PotentialClient> findAllPotentialClient() {
		return potentialClientRepository.findAll();
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer un cliente potencial por su NIF(exacto)
	 * 
	 * @param nif Atributo nif del PotentialClient
	 * @return Objeto PotentialClient de la tabla Usuarios cuyo nif coincide con el porporcionado por par&aacute;metro
	 */
	@Override
	public PotentialClient getPotentialClientByNif(String nif) {
		return (PotentialClient) potentialClientRepository.getByNif(nif);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer un listado de clientes potenciales por contener la cadena suministrada
	 * 
	 * @param nif Cadena de texto parcial o total del nif del PotentialClient
	 * @return Listado de objetos PotentialClient cuyo nif contiene el par&aacute;metro suministrado como par&aacute;metro
	 */
	@Override
	public List<PotentialClient> findPotentialClientByNifContaining(String nif) {
		return potentialClientRepository.findByNifContaining(nif);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer un listado de clientes potenciales por contener la cadena suministrada
	 * 
	 * @param name Cadena de texto parcial o total del nombre del PotentialClient
	 * @return Listado de objetos PotentialClient cuyo name contiene el par&aacute;metro suministrado como par&aacute;metro
	 */
	@Override
	public List<PotentialClient> findPotentialClientByNameContaining(String name) {
		return potentialClientRepository.findByNameContaining(name);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para a&ntilde;adir un cliente potencial a la tabla Usuarios
	 * 
	 * @param c Objeto PotentialClient que se desea insertar
	 */
	@Override
	public void addPotentialClient(PotentialClient c) {
		potentialClientRepository.save(c);		
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para eliminar un cliente potencial por su ID
	 * 
	 * @param IdPotentialClient Long identificador del PotentialClient a eliminar
	 */
	@Override
	public void removePotentialClientById(long IdPotentialClient) {
		potentialClientRepository.deleteById(IdPotentialClient);	
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para actualizar un cliente potencial del sistema
	 * 
	 * @param c Objeto PotentialClient con los datos actualizados
	 */
	@Override
	public void updatePotentialClient(PotentialClient c) {
		potentialClientRepository.save(c);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer el listado de ID de las cuentas bancarias que posee el 
	 * cliente potencial cuyo ID se pasa como par&aacute;metro
	 * 
	 * @param id Long identificador del PotentialClient
	 * @return Listado de Long, identificadores de las cuentas bancarias de las cuales es titular
	 */
	@Override	
	public List<Long> findAccountsIdById(Long id) {
		return potentialClientRepository.findAccountsIdById(id);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer un cliente potencial por su ID
	 * 
	 * @param id Long identificador del cliente potencial
	 * @return Objeto PotentialClient cuyo ID es el proporcionado como par&aacute;metro
	 */
	@Override
	public PotentialClient getById(Long id) {
		return potentialClientRepository.getById(id);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer un listad de clientes potenciales por sus IDs
	 * 
	 * Puede utilizarse para consultas encedenadas o dependientes, de cuentas o la
	 * tabla N:M account_client
	 * 
	 * @param cIds Listado Longs de identificadores de clientes potenciales
	 * @return Listado de objetos PotentialClient cuyos ID se encuentran dentro del listado proporcionado como par&aacute;metro
	 */
	@Override
	public List<PotentialClient> findPotentialClientsById(List<Long> cIds) {
		return potentialClientRepository.findAllById(cIds);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para eliminar registros de clientes potenciales de la tabla client_account
	 * 
	 * @param id Long identificador &uacute;nico del cliente potencial cuyos regitros se desean eliminar
	 */
	@Override
	public void deleteClientAccountReg(Long id) {
		potentialClientRepository.deleteClientAccountReg(id);		
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer los ID de los PotentialClient que poseen alguna cuenta bancaria
	 * 
	 * @return Listado Long de identificadores de clientes potenciales
	 */
	@Override
	public List<Long> findPotentialClientsOwnersId() {
		return potentialClientRepository.findPotentialClientsOwnersId();
	}

}
