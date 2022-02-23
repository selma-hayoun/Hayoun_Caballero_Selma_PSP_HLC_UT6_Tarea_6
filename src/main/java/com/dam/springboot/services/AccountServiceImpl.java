package com.dam.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam.springboot.entities.Account;
import com.dam.springboot.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountServiceI {
	
	@Autowired
	private AccountRepository accRepository;

	@Override
	public List<Account> findAllAccount() {
		return accRepository.findAll();
	}

	@Override
	public Account getAccountByNumAccount(String numAccount) {
		return (Account) accRepository.getByNumAccount(numAccount);
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

	@Override
	public List<Account> findAccountsById(List<Long> accIds) {
		return accRepository.findAllById(accIds);
	}

	@Override
	public Account getById(Long id) {
		return accRepository.getById(id);
	}

	@Override
	public List<Account> findByNumAccountContaining(String numAccount) {
		return accRepository.findByNumAccountContaining(numAccount);
	}

	@Override
	public void addClientAccountReg(Long idClient, Long idAccount) {
		accRepository.addClientAccountReg(idClient, idAccount);	
	}

	@Override
	public List<Long> findPotentialClientsIdById(Long id) {
		return accRepository.findPotentialClientsIdById(id);
	}

	@Override
	public void deleteClientAccountReg(Long id) {
		accRepository.deleteClientAccountReg(id);		
	}

	@Override
	public Integer countOrphanAccounts(Long id) {
		return accRepository.countOrphanAccounts(id);
	}

	@Override
	public List<Account> findAccountsIdNotOwnedById(List<Long> idList) {
		return accRepository.findAccountsIdNotOwnedById(idList);
	}

}
