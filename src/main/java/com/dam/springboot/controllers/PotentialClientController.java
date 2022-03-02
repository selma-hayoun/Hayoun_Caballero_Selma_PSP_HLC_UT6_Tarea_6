package com.dam.springboot.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.dam.springboot.entities.Account;
import com.dam.springboot.entities.PotentialClient;
import com.dam.springboot.services.AccountServiceI;
import com.dam.springboot.services.PotentialClientServiceI;

/**
 * Clase PotentialClientController: controlador para la gesti&oacute;n de clientes potenciales
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Account
 * @see AccountServiceI
 * @see PotentialClient
 * @see PotentialClientServiceI
 *
 */
@Controller
public class PotentialClientController {

	/**
	 * Inyecci&oacute;n de dependencias: Servicio de la tabla Usuarios (clientes potenciales)
	 */
	@Autowired
	private PotentialClientServiceI pClientServiceI;
	
	/**
	 * Inyecci&oacute;n de dependencias: Servicio de la tabla Cuentas
	 */
	@Autowired
	private AccountServiceI accServiceI;
	
	/**
	 * Método para mostrar la vista showPotentialClients con todos los clientes potenciales
	 * 
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar
	 */
	@GetMapping("/showPotentialClientsView")
	public String showPotentialClients(Model model) {
		// Obtención de clientes potenciales
		List<PotentialClient> pcList = pClientServiceI.findAllPotentialClient();
		
		// Carga de datos al modelo: crear clave valor
		model.addAttribute("pClientsListView", pcList);
		model.addAttribute("btnDropPClientEnabled", Boolean.FALSE);
		
		return "showPotentialClients";//HTML-Vista de Templates que muestra los clientes
	}

	/**
	 * Método de la acción de eliminar un cliente potencial
	 * 
	 * @param pClientId Identificador &uacute;nico del cliente potencial
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar: redirige al método {@link #showPotentialClients(Model)}
	 */
	@PostMapping("/actDropPClient")
	public String removePotentialClient(@RequestParam String pClientId, Model model) {
		//Sacamos la situación actual de cuentas del cliente
		List<Long> accountsId = pClientServiceI.findAccountsIdById(Long.valueOf(pClientId));		
		List<Account> clientAccounts = accServiceI.findAccountsById(accountsId);
		
		// Eliminamos sus registros de la tabla N:M		
		pClientServiceI.deleteClientAccountReg(Long.valueOf(pClientId));
		
		// Eliminación de Cliente Potencial
		pClientServiceI.removePotentialClientById(Long.valueOf(pClientId));
		
		//Debemos que buscar las cuentas huérfanas y eliminarlas
		//Estarán en la tabla de Accounts pero no en la N:M	
		for(Account acc : clientAccounts) {
			if(accServiceI.countOrphanAccounts(acc.getId()) == 0) {
				accServiceI.removeAccountById(acc.getId());
			}
		}
		
		return "redirect:showPotentialClientsView";
		//Redirigiendo dentro de un método del controlador 
		//A otro método de la vista
	}
	
	/**
	 * Método de la acción de mostrar las cuentas bancarias de un determinado cliente potencial
	 * 
	 * @param referrer Objeto para mantener la referencia de la &uacute;ltima vista que visit&oacute; y vinvularla al bot&oacute;n volver
	 * @param pClientId Identificador del cliente cuyas cuentas bancarias se desean visualizar
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar: redirige al método {@link AccountController#showAccounts(Model)}
	 */
	@PostMapping("/actAccountsPClient")
	public String showAccountsPotentialClient(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer,@RequestParam String pClientId, Model model) {
		if( referrer != null ) {
		      model.addAttribute("previousUrl", referrer);
		}
		
		List<Long> accountsId = pClientServiceI.findAccountsIdById(Long.valueOf(pClientId));		
		List<Account> clientAccounts = accServiceI.findAccountsById(accountsId);
		
		// Carga de datos al modelo
		model.addAttribute("accListView", clientAccounts);
		model.addAttribute("btnDropAccountEnabled", Boolean.FALSE);
		
		return "showAccounts";
	}
	
	/**
	 * Método para la acción de mostrar búsqueda de clientes potenciales por los parámetros del 
	 * objeto PotentialClient recibido (nif o name).
	 * 
	 * A la vista de listar clientes potenciales se le añade a su modelo la lista resultante de buscar
	 * los par&aacute;metros introducidos por el usuario (uno u otro, teniendo prioridad el nif).
	 * En caso de que el usuario no introduzca ningún campo, se le mostrará un listado con todos los 
	 * clientes potenciales.
	 * 
	 * @param searchedClient Objeto PotentialClient mapeado por la vista con los par&aacute;mentros introducidos por el usuario
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar
	 * @throws Exception Captura las posibles excepciones de mapeo y extracci&oacute;n de datos
	 */
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
		} else if(!StringUtils.hasText(cNif) && !StringUtils.hasText(cName)) {
			//Si ha dejado ambos vacíos mostralos el listado entero
			List<PotentialClient> temp = pClientServiceI.findAllPotentialClient();
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

	/**
	 * Método para la acción de añadir un cliente potencial nuevo
	 * 
	 * @param newClient Objeto PotentialClient mapeado por la vista
	 * @param result Analiza el resultado de la operaci&oacute;n de lo devuelto por la vista, nos sirve para saber si ha habido errores en el mapeo
	 * @return Nombre de la vista a mostrar: redirige al mapeo del método {@link #showPotentialClients(Model)}
	 * @throws Exception Captura las posibles excepciones de mapeo y extracci&oacute;n de datos
	 */
	@PostMapping("/actAddClient")
	private String addNewPotentialClient(@Valid @ModelAttribute PotentialClient newClient, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			if(pClientServiceI.getPotentialClientByNif(newClient.getNif()) != null) {
				throw new Exception("Ya existe un cliente dado de alta con ese NIF.");
			} else {
				throw new Exception("Parámetros de alta erróneos");
			}			
		} else {
			// Se añade el nuevo cliente
			pClientServiceI.addPotentialClient(newClient);		
		}
		return "redirect:showPotentialClientsView";
	}
	
	/**
	 * Método para la acción de actualizar un cliente potencial del sistema
	 * 
	 * @param client Objeto PotentialClient mapeado con los datos a actualizar
	 * @param result Analiza el resultado de la operaci&oacute;n de lo devuelto por la vista, nos sirve para saber si ha habido errores en el mapeo
	 * @return Nombre de la vista a mostrar: redirige al mapeo del método {@link #showPotentialClients(Model)}
	 * @throws Exception Captura las posibles excepciones de mapeo y extracci&oacute;n de datos
	 */
	@PostMapping("/actUpdateClient")
	private String updatePotentialClient(@Valid @ModelAttribute PotentialClient client, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			if(pClientServiceI.getPotentialClientByNif(client.getNif()) != null) {
				throw new Exception("Ya existe un cliente dado de alta con ese NIF.");
			} else {
				throw new Exception("Parámetros de alta erróneos");
			}
		} else {
			pClientServiceI.updatePotentialClient(client);		
		}
		return "redirect:showPotentialClientsView";
	}
	
}
