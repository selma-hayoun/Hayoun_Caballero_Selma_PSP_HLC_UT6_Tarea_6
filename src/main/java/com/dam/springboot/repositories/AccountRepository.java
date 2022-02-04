package com.dam.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.springboot.entities.Account;
import com.dam.springboot.entities.PotentialClient;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account getByNumAccount(String numAccount);
	
	List<Account> findByNumAccountLike(String numAccount);
	
}
