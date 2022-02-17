package com.dam.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dam.springboot.services.PotentialClientServiceI;

@Controller
@RequestMapping("*")
public class SystemController {
	
	@Autowired
	private PotentialClientServiceI pClientServiceI;
	
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
	
	@PostMapping("/updateClientView")
	public String updateClientForm(@RequestParam Long pClientId, Model model) {
		model.addAttribute("myClient", pClientServiceI.getById(pClientId));
		return "updateClient";
	}
	
	//Redirecciona a vista de buscar Clientes
	@GetMapping("/searchClientBy")
	public String showSearchForm() {
		return "searchClientBy";
	}
	
	//Redirecciona al controlador de gestión de cuentas
	@GetMapping("/accountsView")
	public String redirectToCarSearchByTemplate() {
		return "redirect:showAccountsView";//Otro método del controlador
	}
	
	//Para operar
	@GetMapping("/operationView")
	public String redirectToNewCarTemplate() {
		return "index";
	}

}
