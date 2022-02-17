package com.dam.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dam.springboot.services.AccountServiceI;
import com.dam.springboot.services.PotentialClientServiceI;

@Controller
@RequestMapping("*")
public class SystemController {
	
	@Autowired
	private PotentialClientServiceI pClientServiceI;
	
	@Autowired
	private AccountServiceI accountServiceI;
	
	//Capta cualquier solicitud
	@GetMapping
	public String showIndex() {
		return "index";
	}
	
	//Redirecciona al controlador de gestión de clientes: Listado
	@GetMapping("/clientsView")
	public String redirectToClientsController() {
		return "redirect:showPotentialClientsView";
	}
	
	//Redirecciona a vista de añadir Clientes
	@GetMapping("/newClient")
	public String showNewClientForm() {
		return "newClient";
	}
	
	//Redirecciona a la vista de actualizar clientes
	@PostMapping("/updateClientView")
	public String updateClientForm(@RequestParam Long pClientId, Model model) {
		model.addAttribute("myClient", accountServiceI.getById(pClientId));
		return "updateClient";
	}
	
	//Redirecciona a vista de buscar Clientes
	@GetMapping("/searchClientBy")
	public String showClientSearchForm() {
		return "searchClientBy";
	}
	
	//Redirecciona al controlador de gestión de cuentas: Listado
	@GetMapping("/accountsView")
	public String redirectToAccountsController() {
		return "redirect:showAccountsView";
	}
	
	//Redirecciona a vista de añadir cuentas
	@GetMapping("/newAccountView")
	public String showNewAccountForm() {
		return "newAccount";
	}
	
	//Redirecciona a la vista de actualizar cuentas
	@PostMapping("/updateAccountView")
	public String updateAccountForm(@RequestParam Long accId, Model model) {
		model.addAttribute("myAccount", pClientServiceI.getById(accId));
		return "updateAccount";
	}
	
	//Redirecciona a vista de buscar cuentas
	@GetMapping("/searchAccountBy")
	public String showAccountSearchForm() {
		return "searchAccountBy";
	}
	
	//Para operar
	@GetMapping("/operationView")
	public String redirectToNewCarTemplate() {
		return "index";
	}

}
