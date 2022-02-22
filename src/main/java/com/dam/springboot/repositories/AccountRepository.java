package com.dam.springboot.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dam.springboot.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account getByNumAccount(String numAccount);
	
	List<Account> findByNumAccountContaining(String numAccount);
	
//	@Query(value = "DELETE FROM client_account c WHERE c.account_id = :id",
//			nativeQuery = true)
//	void removeAccountRegs(@Param("id") Long id);
	
	@Modifying
	@Query(value = "INSERT INTO client_account(potentialclient_id, account_id) VALUES ( :idClient, :idAccount)",
	nativeQuery = true)
	@Transactional
	void addClientAccountReg(@Param("idClient") Long idClient, @Param("idAccount") Long idAccount);
	
	@Query(value = "SELECT a.potentialclient_id FROM client_account a WHERE a.account_id = :id",
			nativeQuery = true)
	List<Long> findPotentialClientsIdById(@Param("id") Long id);
	
	@Modifying
	@Query(value = "DELETE FROM client_account WHERE account_id = :id",
	nativeQuery = true)
	@Transactional
	void deleteClientAccountReg(@Param("id") Long id);
	
	@Query(value = "SELECT COUNT(*) FROM client_account a WHERE a.account_id = :id",
			nativeQuery = true)
	Integer countOrphanAccounts(@Param("id") Long id);
	 	
	}
