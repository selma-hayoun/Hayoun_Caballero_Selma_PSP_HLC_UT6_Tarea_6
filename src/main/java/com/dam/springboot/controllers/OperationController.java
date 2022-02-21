package com.dam.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dam.springboot.entities.Operation;
import com.dam.springboot.services.OperationServiceI;

@Controller
public class OperationController {

	@Autowired
	private OperationServiceI opServiceI;
	
	@GetMapping("/showOperationsView")
	public String showAccounts(Model model) {
		// Obtención de las operaciones
		List<Operation> opList = opServiceI.findAllOperation();
		
		// Carga de datos al modelo: crear clave valor
		model.addAttribute("opListView", opList);
		model.addAttribute("btnDropOpEnabled", Boolean.FALSE);
		
		return "showOperations";
	}
}
