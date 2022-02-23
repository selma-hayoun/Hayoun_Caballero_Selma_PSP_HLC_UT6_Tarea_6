package com.dam.springboot.services;

import java.util.List;
import com.dam.springboot.entities.Account;

public interface AccountServiceI {
	public List<Account> findAllAccount();
	
	public Account getAccountByNumAccount(String numAccount);
	
	public List<Account> findByNumAccountContaining(String numAccount);
	
	public Account getById(Long id);
	
	public void addAccount(Account acc);	
	
	public void removeAccountById(long IdAccount);
	
	public void updateAccount(Account acc);	
	
	public List<Account> findAccountsById(List<Long> accIds);
	
	public void addClientAccountReg(Long idClient, Long idAccount);
	
	public void deleteClientAccountReg(Long id);
	
	public List<Long> findPotentialClientsIdById(Long id);
	
	public Integer countOrphanAccounts(Long id);
	
	public List<Account> findAccountsIdNotOwnedById(List<Long> idList);

}
