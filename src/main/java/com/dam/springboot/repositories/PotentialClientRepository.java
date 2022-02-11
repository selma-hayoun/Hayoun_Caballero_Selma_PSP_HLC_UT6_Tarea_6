package com.dam.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.springboot.entities.PotentialClient;

@Repository
public interface PotentialClientRepository extends JpaRepository<PotentialClient, Long> {
	
	PotentialClient getByNif(String nif);
	
	List<PotentialClient> findByNifLike(String nif);
	
	List<PotentialClient> findByNameLike(String name);	
	
}
