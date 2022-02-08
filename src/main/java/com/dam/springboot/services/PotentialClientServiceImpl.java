package com.dam.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dam.springboot.entities.PotentialClient;
import com.dam.springboot.repositories.PotentialClientRepository;

public class PotentialClientServiceImpl implements PotentialClientServiceI{

	@Autowired
	private PotentialClientRepository potentialClientRepository;	
	
	@Override
	public List<PotentialClient> findAllPotentialClient() {
		return potentialClientRepository.findAll();
	}

	@Override
	public PotentialClient getPotentialClientByNif(String nif) {
		return potentialClientRepository.getByNif(nif);
	}

	@Override
	public List<PotentialClient> findPotentialClientByNifLike(String nif) {
		return potentialClientRepository.findByNifLike(nif);
	}

	@Override
	public List<PotentialClient> findPotentialClientByNameLike(String name) {
		return potentialClientRepository.findByNameLike(name);
	}

	@Override
	public void addPotentialClient(PotentialClient c) {
		potentialClientRepository.save(c);		
	}

	@Override
	public void removePotentialClientById(long IdPotentialClient) {
		potentialClientRepository.deleteById(IdPotentialClient);	
	}

	@Override
	public void updatePotentialClient(PotentialClient c) {
		potentialClientRepository.save(c);
	}

}
