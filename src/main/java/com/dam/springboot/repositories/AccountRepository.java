package com.dam.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.springboot.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account getByNumAccount(String numAccount);
	
	List<Account> findByNumAccountLike(String numAccount);
 	
}
