package com.dam.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dam.springboot.entities.PotentialClient;
import com.dam.springboot.services.PotentialClientServiceI;

@Controller
//@RequestMapping("/api")
public class PotentialClientController {

	@Autowired
	private PotentialClientServiceI pClientServiceI;
	
	@RequestMapping("/home")
	@ResponseBody
	public String home() {
		return "hello world";
	}
	
	@GetMapping("/showPotentialClientsView")
	public String showPotentialClients(Model model) {
		// Obtención de clientes potenciales
		List<PotentialClient> pcList = pClientServiceI.findAllPotentialClient();
		
		// Carga de datos al modelo: crear clave valor
		model.addAttribute("pClientsListView", pcList);
		model.addAttribute("btnDropPClientEnabled", Boolean.FALSE);
		
		return "showPotentialClients";//HTML-Vista de Templates que muestra los coches
	}
	
	@PostMapping("/actDropPClient")
	public String removePotentialClient(@RequestParam String pClientId, Model model) {
		// Eliminación de Cliente Potencial
		pClientServiceI.removePotentialClientById(Long.valueOf(pClientId));
		
		return "redirect:showPotentialClientsView";//Redirigiendo dentro de un método del controlador 
		//A otro método de la vista
	}
}
