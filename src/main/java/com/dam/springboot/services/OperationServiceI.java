package com.dam.springboot.services;

import java.util.List;

import com.dam.springboot.entities.Operation;

public interface OperationServiceI {
	public List<Operation> findAllOperation();
	
	public void addAccount(Operation op);
	
	public void removeAccountById(long IdOperation);
	
	public void updateAccount(Operation op);	
}
