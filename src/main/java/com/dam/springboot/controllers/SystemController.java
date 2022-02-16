package com.dam.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("*")
public class SystemController {
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
	
	//Redirecciona a vista de buscar Clientes
	@GetMapping("/searchClientBy")
	public String showSearchForm() {
		return "searchClientBy";
	}
	
	//Redirecciona al controlador de gestión de cuentas
	@GetMapping("/accountsView")
	public String redirectToCarSearchByTemplate() {
		return "redirect:showAccountsView";
	}
	
	//Para operar
	@GetMapping("/operationView")
	public String redirectToNewCarTemplate() {
		return "index";
	}

}
