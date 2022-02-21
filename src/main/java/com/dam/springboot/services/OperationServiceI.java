package com.dam.springboot.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.dam.springboot.entities.Account;
import com.dam.springboot.entities.Operation;

public interface OperationServiceI {
	
	public List<Operation> findAllOperation();
	
	public List<Operation> findOperationsById(List<Long> opIds);	
	
	public void addAccount(Operation op);
	
	public void removeAccountById(long IdOperation);
	
	public void updateAccount(Operation op);	
	
	public List<Operation> findOperationsByAccountId(Long id);
}
