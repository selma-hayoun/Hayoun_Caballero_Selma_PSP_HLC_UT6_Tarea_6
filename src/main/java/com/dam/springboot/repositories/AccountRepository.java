package com.dam.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dam.springboot.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account getByNumAccount(String numAccount);
	
	List<Account> findByNumAccountContaining(String numAccount);
	
	@Query(value = "DELETE FROM client_account c WHERE c.account_id = :id",
			nativeQuery = true)
	void removeAccountRegs(@Param("id") Long id);
 	
}
