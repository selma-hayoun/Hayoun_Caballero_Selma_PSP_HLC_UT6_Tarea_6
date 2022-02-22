package com.dam.springboot.models;

import lombok.Getter;
import lombok.Setter;

public class OperationModel {
	@Getter @Setter private Long id;
	
	@Getter @Setter private String amount;
	
	@Getter @Setter private Long accountId;

	@Override
	public String toString() {
		return "OperationModel [id=" + id + ", amount=" + amount + ", accountId=" + accountId + "]";
	}

}
