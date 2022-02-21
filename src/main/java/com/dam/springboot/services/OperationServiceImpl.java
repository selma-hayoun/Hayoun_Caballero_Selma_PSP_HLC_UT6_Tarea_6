package com.dam.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam.springboot.entities.Operation;
import com.dam.springboot.repositories.OperationRepository;

@Service
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

	@Override
	public List<Operation> findOperationsById(List<Long> opIds) {
		return opRepository.findAllById(opIds);
	}

	@Override
	public List<Operation> findOperationsByAccountId(Long id) {
		return opRepository.findOperationsByAccountId(id);
	}

}
