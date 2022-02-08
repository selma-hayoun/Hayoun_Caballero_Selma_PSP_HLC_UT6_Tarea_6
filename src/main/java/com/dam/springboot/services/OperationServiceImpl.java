package com.dam.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dam.springboot.entities.Operation;
import com.dam.springboot.repositories.OperationRepository;

public class OperationServiceImpl implements OperationServiceI{

	@Autowired
	private OperationRepository opRepository;
	
	@Override
	public List<Operation> findAllOperation() {
		return opRepository.findAll();
	}

	@Override
	public void addAccount(Operation op) {
		opRepository.save(op);		
	}

	@Override
	public void removeAccountById(long IdOperation) {
		opRepository.deleteById(IdOperation);		
	}

	@Override
	public void updateAccount(Operation op) {
		opRepository.save(op);		
	}

}
