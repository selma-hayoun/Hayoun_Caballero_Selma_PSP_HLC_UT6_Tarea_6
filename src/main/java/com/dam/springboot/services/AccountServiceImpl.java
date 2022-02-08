package com.dam.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dam.springboot.entities.Account;
import com.dam.springboot.repositories.AccountRepository;

public class AccountServiceImpl implements AccountServiceI {
	
	@Autowired
	private AccountRepository accRepository;

	@Override
	public List<Account> findAllAccount() {
		return accRepository.findAll();
	}

	@Override
	public Account getAccountByNumAccount(String numAccount) {
		return accRepository.getByNumAccount(numAccount);
	}

	@Override
	public List<Account> findAccountByNumAccountLike(String numAccount) {
		return accRepository.findByNumAccountLike(numAccount);
	}

	@Override
	public void addAccount(Account acc) {
		accRepository.save(acc);		
	}

	@Override
	public void removeAccountById(long IdAccount) {
		accRepository.deleteById(IdAccount);
	}

	@Override
	public void updateAccount(Account acc) {
		accRepository.save(acc);
	}

}
