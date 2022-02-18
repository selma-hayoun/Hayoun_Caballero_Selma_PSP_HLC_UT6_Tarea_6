package com.dam.springboot.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dam.springboot.entities.Account;
import com.dam.springboot.models.AccountModel;
import com.dam.springboot.services.AccountServiceI;

@Controller
public class AccountController {

	@Autowired
	private AccountServiceI accServiceI;
	
//	@RequestMapping("/home")
//	@ResponseBody
//	public String home() {
//		return "HOME DE CUENTAS";
//	}
	
	@GetMapping("/showAccountsView")
	public String showAccounts(Model model) {
		// Obtención de las cuentas
		List<Account> accList = accServiceI.findAllAccount();
		
		// Carga de datos al modelo: crear clave valor
		model.addAttribute("accListView", accList);
		model.addAttribute("btnDropAccEnabled", Boolean.FALSE);
		
		return "showAccounts";
	}

	
	@PostMapping("/actDropAccount")
	public String removeAccount(@RequestParam String accId, Model model) {
		// Eliminación de Cuenta
		accServiceI.removeAccountById(Long.valueOf(accId));		
		return "redirect:showAccountsView";
	}
	

	@PostMapping("/actSearchAccount")
	public String submitSearchAccountForm(@ModelAttribute Account searchedAccount, Model model) throws Exception {

		List<Account> accList = new ArrayList<Account>();
		
		System.out.println(searchedAccount.getNumAccount());

		String accNum = searchedAccount.getNumAccount();
		
		if (StringUtils.hasText(accNum)) {
			// Búsqueda por Número de cuenta (no exacto)
			List<Account> temp = accServiceI.findByNumAccountContaining(accNum);
			if (temp != null) {
				accList.addAll(temp);
			}
		} else {
			throw new Exception("Parámetros de búsqueda erróneos.");
		}
		// Carga de datos al modelo
		model.addAttribute("accListView", accList);
		model.addAttribute("btnDropAccountEnabled", Boolean.FALSE);

		return "showAccounts";
	}

	@PostMapping("/actAddAccount")
	private String addNewAccount(@Valid @ModelAttribute AccountModel newAccountModel, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			System.out.println(newAccountModel.toString());
			// Creating the LocalDatetime object
			LocalDate currentLocalDate = LocalDate.now();		
			// Getting system timezone
			ZoneId systemTimeZone = ZoneId.systemDefault();		
			// converting LocalDateTime to ZonedDateTime with the system timezone
			ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);		
			// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
			Date utilDate = Date.from(zonedDateTime.toInstant());
			System.out.println(utilDate.toString());
			throw new Exception("Parámetros de alta erróneos");
			
		} else {
			Account newAccount = new Account();
			newAccount.setNumAccount(newAccountModel.getNumAccount());
			// Creating the LocalDatetime object
			LocalDate currentLocalDate = LocalDate.now();		
			// Getting system timezone
			ZoneId systemTimeZone = ZoneId.systemDefault();		
			// converting LocalDateTime to ZonedDateTime with the system timezone
			ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);		
			// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
			Date utilDate = Date.from(zonedDateTime.toInstant());
			// Seteamos la fecha actual
			newAccount.setCreateAt(utilDate);
			
			double balance;
			try {
				balance = Double.parseDouble(newAccountModel.getBalance());
			} catch (Exception e) {
				balance = 0;
			}
			newAccount.setBalance(balance);
			System.out.println(newAccount.toString());
			// Se añade la nueva cuenta
			accServiceI.addAccount(newAccount);		
		}
		return "redirect:showAccountsView";
	}
	
	@PostMapping("/actUpdateAccount")
	private String updateAccount(@Valid @ModelAttribute Account account, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de alta erróneos");
		} else {
			accServiceI.updateAccount(account);
		}
		return "redirect:showAccountsView";
	}
	
}
