package com.dam.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dam.springboot.entities.Operation;
import com.dam.springboot.services.OperationServiceI;
import com.dam.springboot.services.OperationServiceImpl;

/**
 * Clase OperationRepository
 * 
 * Repositorio de nuestra tabla Operaciones.
 * Nos permite dise&ntilde;ar m&eacute;todos personalizados de trabajo
 * en esta tabla.
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Operation
 * @see OperationServiceI
 * @see OperationServiceImpl
 *
 */
@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
	
	/**
	 * M&eacute;todo para extraer las operaciones de una cuenta bancaria
	 * 
	 * @param id Long identificador &uacute;nico del objeto Account
	 * @return Lista de operaciones vinculadas a ese ID (Clave for&aacute;nea)
	 */
	@Query(value = "SELECT * FROM operaciones op WHERE op.account_id = :id",
			nativeQuery = true)
	List<Operation> findOperationsByAccountId(@Param("id") Long id);
	
	/**
	 * M&eacute;todo para extraer todas las operaciones del sistema ordenadas por fecha (desdencente)
	 * 
	 * @return Listado de objetos Operation ordenados
	 */
	@Query(value = "SELECT * FROM operaciones op ORDER BY op.fecha_operacion DESC",
			nativeQuery = true)
	List<Operation> findOperationsOrderByDate();
}
