package com.dam.springboot.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.dam.springboot.entities.Account;
import com.dam.springboot.entities.Operation;

public interface OperationServiceI {
	
	public List<Operation> findAllOperation();
	
	public List<Operation> findOperationsById(List<Long> opIds);	
	
	public void addOperation(Operation op);
	
	public void removeOperationById(long IdOperation);
	
	public void updateOperation(Operation op);	
	
	public List<Operation> findOperationsByAccountId(Long id);
}
