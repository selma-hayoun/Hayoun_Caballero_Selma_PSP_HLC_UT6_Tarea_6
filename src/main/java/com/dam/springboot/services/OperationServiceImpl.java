package com.dam.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam.springboot.entities.Operation;
import com.dam.springboot.repositories.OperationRepository;

/**
 * Clase OperationServiceImpl: Implementa los m&eacute;todos de la interfaz de servicio OperationServiceI
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Operation
 * @see OperationServiceI
 *
 */
@Service
public class OperationServiceImpl implements OperationServiceI{

	/**
	 * Inyecci&oacute;n de dependencias: Repositorio de la tabla Operaciones
	 */
	@Autowired
	private OperationRepository opRepository;
	
	/**
	 * Implementaci&oacute;n del m&eacute;todo para listar todas las operaciones registradas en nuestro sistema
	 * 
	 * @return Lista de objetos Operation
	 */
	@Override
	public List<Operation> findAllOperation() {
		return opRepository.findAll();
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para a&ntilde;adir una operaci&oacute;n a la tabla Operaciones
	 * 
	 * @param op Objeto Operation que se desea insertar
	 */
	@Override
	public void addOperation(Operation op) {
		opRepository.save(op);		
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para eliminar una operaci&oacute;n por su ID
	 * 
	 * @param IdOperation Long identificador &uacute;nico de la operaci&oacute;n a eliminar
	 */
	@Override
	public void removeOperationById(long IdOperation) {
		opRepository.deleteById(IdOperation);		
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para actualizar los datos de un registro de la tabla Operaciones
	 * 
	 * @param op Objeto Operation actualizado
	 */
	@Override
	public void updateOperation(Operation op) {
		opRepository.save(op);		
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer un listado de operaciones seg&uacute;n un listado de IDs de operaciones
	 * proporcionados
	 * 
	 * @param opIds Listado de Long, identificadores &uacute;nicos de operaciones
	 * @return Lista de objetos Operation cuyo ID coindice con alguno de los proporcionados como par&aacute;metro
	 */
	@Override
	public List<Operation> findOperationsById(List<Long> opIds) {
		return opRepository.findAllById(opIds);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer las operaciones de una cuenta bancaria
	 * 
	 * @param id Long identificador &uacute;nico del objeto Account
	 * @return Lista de operaciones vinculadas a ese ID (Clave for&aacute;nea)
	 */
	@Override
	public List<Operation> findOperationsByAccountId(Long id) {
		return opRepository.findOperationsByAccountId(id);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer todas las operaciones del sistema ordenadas por fecha (desdencente)
	 * 
	 * @return Listado de objetos Operation ordenados
	 */
	@Override
	public List<Operation> findOperationsOrderByDate() {
		return opRepository.findOperationsOrderByDate();
	}

}
