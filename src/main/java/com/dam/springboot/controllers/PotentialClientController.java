package com.dam.springboot.controllers;

import java.util.ArrayList;
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

import com.dam.springboot.entities.PotentialClient;
import com.dam.springboot.services.PotentialClientServiceI;

@Controller
//@RequestMapping("/api")
public class PotentialClientController {

	@Autowired
	private PotentialClientServiceI pClientServiceI;
	
//	@RequestMapping("/home")
//	@ResponseBody
//	public String home() {
//		return "HOME DE CLIENTES";
//	}
	
	@GetMapping("/showPotentialClientsView")
	public String showPotentialClients(Model model) {
		// Obtención de clientes potenciales
		List<PotentialClient> pcList = pClientServiceI.findAllPotentialClient();
		
		// Carga de datos al modelo: crear clave valor
		model.addAttribute("pClientsListView", pcList);
		model.addAttribute("btnDropPClientEnabled", Boolean.FALSE);
		
		return "showPotentialClients";//HTML-Vista de Templates que muestra los clientes
	}

	
	@PostMapping("/actDropPClient")
	public String removePotentialClient(@RequestParam String pClientId, Model model) {
		// Eliminación de Cliente Potencial
		pClientServiceI.removePotentialClientById(Long.valueOf(pClientId));
		
		return "redirect:showPotentialClientsView";//Redirigiendo dentro de un método del controlador 
		//A otro método de la vista
	}
	

	@PostMapping("/actSearchClient")
	public String submitSearchClientForm(@ModelAttribute PotentialClient searchedClient, Model model) throws Exception {

		List<PotentialClient> myClientsList = new ArrayList<PotentialClient>();
		
		System.out.println(searchedClient.getNif());

		String cNif = searchedClient.getNif();
		String cName = searchedClient.getName();
		
		if (StringUtils.hasText(cNif)) {
			// Búsqueda por NIF
			List<PotentialClient> temp = pClientServiceI.findPotentialClientByNifContaining(cNif);
			if (temp != null) {
				myClientsList.addAll(temp);
			}
		} else if (!StringUtils.hasText(cNif) && (StringUtils.hasText(cName))) {
			// Búsqueda por NAME
			List<PotentialClient> temp = pClientServiceI.findPotentialClientByNameContaining(cName);
			if (temp != null) {
				myClientsList.addAll(temp);
			}
		} else {
			throw new Exception("Parámetros de búsqueda erróneos.");
		}
		// Carga de datos al modelo
		model.addAttribute("pClientsListView", myClientsList);
		model.addAttribute("btnDropPClientEnabled", Boolean.FALSE);

		return "showPotentialClients";
	}

	@PostMapping("/actAddClient")
	private String addNewPotentialClient(@Valid @ModelAttribute PotentialClient newClient, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de alta erróneos");
		} else {
			// Se añade el nuevo cliente
			pClientServiceI.addPotentialClient(newClient);		
		}
		return "redirect:showPotentialClientsView";
	}
	
	@PostMapping("/actUpdateClient")
	private String updatePotentialClient(@Valid @ModelAttribute PotentialClient client, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de alta erróneos");
		} else {
			pClientServiceI.updatePotentialClient(client);		
		}
		return "redirect:showPotentialClientsView";
	}
	
}
