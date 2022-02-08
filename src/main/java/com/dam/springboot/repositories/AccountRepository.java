package com.dam.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.springboot.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account getByNumAccount(String numAccount);
	
	List<Account> findByNumAccountLike(String numAccount);
	
}
