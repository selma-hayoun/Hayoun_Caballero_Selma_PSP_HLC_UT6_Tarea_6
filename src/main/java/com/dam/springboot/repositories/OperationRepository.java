package com.dam.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.springboot.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}
