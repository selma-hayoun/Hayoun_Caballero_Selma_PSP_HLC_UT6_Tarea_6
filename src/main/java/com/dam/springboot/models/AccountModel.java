package com.dam.springboot.models;

import lombok.Getter;
import lombok.Setter;

public class AccountModel {
	@Getter @Setter private Long id;
	
	@Getter @Setter private String numAccount;
	
	@Getter @Setter private String balance;
	
	@Getter @Setter private Long[] myOwners;

	@Override
	public String toString() {
		return "AccountModel [id=" + id + ", numAccount=" + numAccount + ", balance="
				+ balance + "]";
	}
	
}
