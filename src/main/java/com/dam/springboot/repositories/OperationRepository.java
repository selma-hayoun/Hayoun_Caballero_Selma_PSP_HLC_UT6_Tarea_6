package com.dam.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dam.springboot.entities.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
	@Query(value = "SELECT * FROM operaciones op WHERE op.account_id = :id",
			nativeQuery = true)
	List<Operation> findOperationsByAccountId(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM operaciones op ORDER BY op.fecha_operacion DESC",
			nativeQuery = true)
	List<Operation> findOperationsOrderByDate();
}
