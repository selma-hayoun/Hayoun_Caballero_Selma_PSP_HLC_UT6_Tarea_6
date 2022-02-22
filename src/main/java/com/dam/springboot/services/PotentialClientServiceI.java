package com.dam.springboot.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.dam.springboot.entities.Account;
import com.dam.springboot.entities.PotentialClient;

public interface PotentialClientServiceI {

	public List<PotentialClient> findAllPotentialClient();
	
	public PotentialClient getPotentialClientByNif(String nif);
	
	public PotentialClient getById(Long id);
	
	public List<PotentialClient> findPotentialClientByNifContaining(String nif);
	
	public List<PotentialClient> findPotentialClientByNameContaining(String name);
	
	public void addPotentialClient(PotentialClient c);
	
//	public void removePotentialClientRegs(long IdPotentialClient);
	
	public void removePotentialClientById(long IdPotentialClient);
	
	public void updatePotentialClient(PotentialClient c);	
	
	public List<Long> findAccountsIdById(Long id);
	
	public List<PotentialClient> findPotentialClientsById(List<Long> cIds);
	
	public void deleteClientAccountReg(Long id);
	
}
