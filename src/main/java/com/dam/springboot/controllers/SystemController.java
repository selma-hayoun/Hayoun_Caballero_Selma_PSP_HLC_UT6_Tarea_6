package com.dam.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dam.springboot.entities.PotentialClient;
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
	@GetMapping("/newClientView")
	public String showNewClientForm() {
		return "newClient";
	}
	
	//Redirecciona a la vista de actualizar clientes
	@PostMapping("/updateClientView")
	public String updateClientForm(@RequestParam Long pClientId, Model model) {
		model.addAttribute("myClient", pClientServiceI.getById(pClientId));
		return "updateClient";
	}
	
	//Redirecciona a vista de buscar Clientes
	@GetMapping("/searchClientByView")
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
//	public String showNewAccountForm() {
	public String showNewAccountForm(Model model) {
		model.addAttribute("myPClients", pClientServiceI.findAllPotentialClient());
		return "newAccount";
	}
	
	//Redirecciona a la vista de actualizar cuentas
	@PostMapping("/updateAccountView")
	public String updateAccountForm(@RequestParam Long accId, Model model) {
		model.addAttribute("myAcc", accountServiceI.getById(accId));
//		model.addAttribute("pClients", pClientServiceI.findAllPotentialClient());
		model.addAttribute("myPClients", pClientServiceI.findPotentialClientsById(accountServiceI.findPotentialClientsIdById(accId)));
		return "updateAccount";
	}
	
	//Redirecciona a vista de buscar cuentas
	@GetMapping("/searchAccountByView")
	public String showAccountSearchForm() {
		return "searchAccountBy";
	}
	
	//Para operar
	@GetMapping("/operationsView")
	public String redirectToOperationsController() {
		return "redirect:showOperationsView";
	}

}
