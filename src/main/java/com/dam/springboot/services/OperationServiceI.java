package com.dam.springboot.services;

import java.util.List;

import com.dam.springboot.entities.Operation;
import com.dam.springboot.repositories.OperationRepository;

/**
 * Clase OperationServiceI: Interfaz del servicio para tabla Operaciones
 * 
 * Interfaz que recoge todos los m&eacute;todos de CRUD para nuestra tabla Operaciones
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Operation
 * @see OperationRepository
 * @see OperationServiceImpl
 *
 */
public interface OperationServiceI {
	
	/**
	 * M&eacute;todo para listar todas las operaciones registradas en nuestro sistema
	 * 
	 * @return Lista de objetos Operation
	 */
	public List<Operation> findAllOperation();
	
	/**
	 * M&eacute;todo para extraer un listado de operaciones seg&uacute;n un listado de IDs de operaciones
	 * proporcionados
	 * 
	 * @param opIds Listado de Long, identificadores &uacute;nicos de operaciones
	 * @return Lista de objetos Operation cuyo ID coindice con alguno de los proporcionados como par&aacute;metro
	 */
	public List<Operation> findOperationsById(List<Long> opIds);	
	
	/**
	 * M&eacute;todo para a&ntilde;adir una operaci&oacute;n a la tabla Operaciones
	 * 
	 * @param op Objeto Operation que se desea insertar
	 */
	public void addOperation(Operation op);
	
	/**
	 * M&eacute;todo para eliminar una operaci&oacute;n por su ID
	 * 
	 * @param IdOperation Long identificador &uacute;nico de la operaci&oacute;n a eliminar
	 */
	public void removeOperationById(long IdOperation);
	
	/**
	 * M&eacute;todo para actualizar los datos de un registro de la tabla Operaciones
	 * 
	 * @param op Objeto Operation actualizado
	 */
	public void updateOperation(Operation op);	
	
	/**
	 * M&eacute;todo para extraer las operaciones de una cuenta bancaria
	 * 
	 * @param id Long identificador &uacute;nico del objeto Account
	 * @return Lista de operaciones vinculadas a ese ID (Clave for&aacute;nea)
	 */
	public List<Operation> findOperationsByAccountId(Long id);
	
	/**
	 * M&eacute;todo para extraer todas las operaciones del sistema ordenadas por fecha (desdencente)
	 * 
	 * @return Listado de objetos Operation ordenados
	 */
	public List<Operation> findOperationsOrderByDate();
}
