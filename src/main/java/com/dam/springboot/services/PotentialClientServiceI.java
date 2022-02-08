package com.dam.springboot.services;

import java.util.List;

import com.dam.springboot.entities.PotentialClient;

public interface PotentialClientServiceI {

	public List<PotentialClient> findAllPotentialClient();
	
	public PotentialClient getPotentialClientByNif(String nif);
	
	public List<PotentialClient> findPotentialClientByNifLike(String nif);
	
	public List<PotentialClient> findPotentialClientByNameLike(String name);
	
	public void addPotentialClient(PotentialClient c);
	
	public void removePotentialClientById(long IdPotentialClient);
	
	public void updatePotentialClient(PotentialClient c);	
	
}
