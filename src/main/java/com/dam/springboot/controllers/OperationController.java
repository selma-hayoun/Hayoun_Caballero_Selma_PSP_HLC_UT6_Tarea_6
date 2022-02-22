package com.dam.springboot.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dam.springboot.entities.Account;
import com.dam.springboot.entities.Operation;
import com.dam.springboot.entities.OperationType;
import com.dam.springboot.models.AccountModel;
import com.dam.springboot.models.OperationModel;
import com.dam.springboot.services.AccountServiceI;
import com.dam.springboot.services.OperationServiceI;

@Controller
public class OperationController {

	@Autowired
	private OperationServiceI opServiceI;
	
	@Autowired
	private AccountServiceI accServiceI;
	
	@GetMapping("/showOperationsView")
	public String showAccounts(Model model) {
		// Obtención de las operaciones
		List<Operation> opList = opServiceI.findAllOperation();
		
		// Carga de datos al modelo: crear clave valor
		model.addAttribute("opListView", opList);
		model.addAttribute("btnDropOpEnabled", Boolean.FALSE);
		
		return "showOperations";
	}
	
	@PostMapping("/actDeposit")
	private String addDeposit(@Valid @ModelAttribute OperationModel newOpModel, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de alta erróneos");			
		} else {
			Operation newOp = new Operation();			
			
			// Creating the LocalDatetime object
			LocalDate currentLocalDate = LocalDate.now();		
			// Getting system timezone
			ZoneId systemTimeZone = ZoneId.systemDefault();		
			// converting LocalDateTime to ZonedDateTime with the system timezone
			ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);		
			// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
			Date utilDate = Date.from(zonedDateTime.toInstant());
			
			// Seteamos la fecha actual
			newOp.setCreateAt(utilDate);
			
			//Seteamos el tipo de operación
			newOp.setOp(OperationType.DEPOSIT);
			
			double amount;
			try {
				amount = Double.parseDouble(newOpModel.getAmount());
			} catch (Exception e) {
				amount = 0;
			}
			
			//Seteamos la cantidad
			newOp.setAmount(amount);
			
			//Seteamos el id de la cuenta
			newOp.setAccount_id(newOpModel.getAccountId());
			
			System.out.println(newOp.toString());
			System.out.println(newOpModel.toString());
			
			// Se añade la nueva operación
			opServiceI.addOperation(newOp);	
			
			//Se actualiza el balance de la cuenta
			Account acc = accServiceI.getById(newOpModel.getAccountId());
			acc.setBalance(acc.getBalance() + newOp.getAmount());
			accServiceI.updateAccount(acc);
						
		}
		return "redirect:newDepositWithdrawalView";
	}
	
	@PostMapping("/actWithdrawal")
	private String addWithdrawal(@Valid @ModelAttribute OperationModel newOpModel, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de alta erróneos");			
		} else {
			//Lo primero de todo es comprobar que tiene saldo
			//Si no lo tuviera cancelamos la operación
			double amount;
			try {
				amount = Double.parseDouble(newOpModel.getAmount());
			} catch (Exception e) {
				amount = 0;
			}
			
			if(accServiceI.getById(newOpModel.getAccountId()).getBalance() < amount) {
				throw new Exception("Operación cancelada: no tiene suficiente saldo en su cuenta para retirar la cantidad solicitada.");
			} else {
				Operation newOp = new Operation();			
				
				// Creating the LocalDatetime object
				LocalDate currentLocalDate = LocalDate.now();		
				// Getting system timezone
				ZoneId systemTimeZone = ZoneId.systemDefault();		
				// converting LocalDateTime to ZonedDateTime with the system timezone
				ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);		
				// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
				Date utilDate = Date.from(zonedDateTime.toInstant());
				
				// Seteamos la fecha actual
				newOp.setCreateAt(utilDate);
				
				//Seteamos el tipo de operación
				newOp.setOp(OperationType.WITHDRAWAL);					
				
				//Seteamos la cantidad
				newOp.setAmount(amount);
				
				//Seteamos el id de la cuenta
				newOp.setAccount_id(newOpModel.getAccountId());
				
				System.out.println(newOp.toString());
				System.out.println(newOpModel.toString());
				
				// Se añade la nueva operación
				opServiceI.addOperation(newOp);	
				
				//Se actualiza el balance de la cuenta
				Account acc = accServiceI.getById(newOpModel.getAccountId());
				acc.setBalance(acc.getBalance() - newOp.getAmount());
				accServiceI.updateAccount(acc);				
			}		
						
		}
		return "redirect:newDepositWithdrawalView";
	}
}
