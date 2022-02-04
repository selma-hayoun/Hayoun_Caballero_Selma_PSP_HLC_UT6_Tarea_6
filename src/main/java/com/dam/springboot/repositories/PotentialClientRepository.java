package com.dam.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.springboot.entities.PotentialClient;

public interface PotentialClientRepository extends JpaRepository<PotentialClient, Long> {

}
