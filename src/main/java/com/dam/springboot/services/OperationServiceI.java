package com.dam.springboot.services;

import java.util.List;

import com.dam.springboot.entities.Operation;
import com.dam.springboot.repositories.OperationRepository;

/**
 * Clase OperationServiceI: Interfaz del servicio para tabla Operaciones
 * 
 * Interfaz que recoge todos los métodos de CRUD para nuestra tabla Operaciones
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
	 * Método para listar todas las operaciones registradas en nuestro sistema
	 * 
	 * @return Lista de objetos Operation
	 */
	public List<Operation> findAllOperation();
	
	/**
	 * Método para extraer un listado de operaciones según un listado de IDs de operaciones
	 * proporcionados
	 * 
	 * @param opIds Listado de Long, identificadores únicos de operaciones
	 * @return Lista de objetos Operation cuyo ID coindice con alguno de los proporcionados como parámetro
	 */
	public List<Operation> findOperationsById(List<Long> opIds);	
	
	/**
	 * Método para añadir una operación a la tabla Operaciones
	 * 
	 * @param op Objeto Operation que se desea insertar
	 */
	public void addOperation(Operation op);
	
	/**
	 * Método para eliminar una operación por su ID
	 * 
	 * @param IdOperation Long identificador único de la operación a eliminar
	 */
	public void removeOperationById(long IdOperation);
	
	/**
	 * Método para actualizar los datos de un registro de la tabla Operaciones
	 * 
	 * @param op Objeto Operation actualizado
	 */
	public void updateOperation(Operation op);	
	
	/**
	 * Método para extraer las operaciones de una cuenta bancaria
	 * 
	 * @param id Long identificador único del objeto Account
	 * @return Lista de operaciones vinculadas a ese ID (Clave foránea)
	 */
	public List<Operation> findOperationsByAccountId(Long id);
	
	/**
	 * Método para extraer todas las operaciones del sistema ordenadas por fecha (desdencente)
	 * 
	 * @return Listado de objetos Operation ordenados
	 */
	public List<Operation> findOperationsOrderByDate();
}
