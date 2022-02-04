package com.dam.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.springboot.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
