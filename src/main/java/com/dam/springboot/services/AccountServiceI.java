package com.dam.springboot.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.dam.springboot.entities.Account;

public interface AccountServiceI {
	public List<Account> findAllAccount();
	
	public Account getAccountByNumAccount(String numAccount);
	
	public List<Account> findByNumAccountContaining(String numAccount);
	
	public Account getById(Long id);
	
	public void addAccount(Account acc);
	
//	public void removeAccountRegs(long IdAccount);
	
	public void removeAccountById(long IdAccount);
	
	public void updateAccount(Account acc);	
	
	public List<Account> findAccountsById(List<Long> accIds);
	
	public void addClientAccountReg(@Param("idClient") Long idClient, @Param("idAccount") Long idAccount);
	
	public void deleteClientAccountReg(@Param("id") Long id);
	
	public List<Long> findPotentialClientsIdById(@Param("id") Long id);
	
	public Integer countOrphanAccounts(Long id);
	
	public List<Account> findAccountsIdNotOwnedById(List<Long> idList);

}
