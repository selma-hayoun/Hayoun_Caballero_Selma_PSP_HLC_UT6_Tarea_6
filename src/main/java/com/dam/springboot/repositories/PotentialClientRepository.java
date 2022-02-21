package com.dam.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.dam.springboot.entities.PotentialClient;

@Repository
public interface PotentialClientRepository extends JpaRepository<PotentialClient, Long> {
	
	PotentialClient getByNif(String nif);
	
	List<PotentialClient> findByNifContaining(String nif);
	
	List<PotentialClient> findByNameContaining(String name);	
	
	@Query(value = "SELECT c.account_id FROM client_account c WHERE c.potentialclient_id = :id",
			nativeQuery = true)
	List<Long> findAccountsIdById(@Param("id") Long id);
	
	@Query(value = "DELETE FROM client_account c WHERE c.potentialclient_id = :id",
			nativeQuery = true)
	void removePotentialClientRegs(@Param("id") Long id);
	
}
