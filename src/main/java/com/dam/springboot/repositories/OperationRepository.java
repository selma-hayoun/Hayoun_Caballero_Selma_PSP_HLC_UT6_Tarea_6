package com.dam.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.springboot.entities.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

}
