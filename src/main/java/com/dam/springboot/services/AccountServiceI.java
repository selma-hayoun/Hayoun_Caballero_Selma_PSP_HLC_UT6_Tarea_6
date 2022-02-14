package com.dam.springboot.services;

import java.util.List;

import com.dam.springboot.entities.Account;

public interface AccountServiceI {
	public List<Account> findAllAccount();
	
	public Account getAccountByNumAccount(String numAccount);
	
	public List<Account> findAccountByNumAccountLike(String numAccount);
	
	public void addAccount(Account acc);
	
	public void removeAccountById(long IdAccount);
	
	public void updateAccount(Account acc);	
	
	public List<Account> findAccountsById(List<Long> accIds);

}
