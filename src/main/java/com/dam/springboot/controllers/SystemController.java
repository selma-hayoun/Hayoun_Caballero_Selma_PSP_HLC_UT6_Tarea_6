package com.dam.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dam.springboot.services.AccountServiceI;
import com.dam.springboot.services.PotentialClientServiceI;
import com.dam.springboot.entities.*;

/**
 * Clase SystemController para gesti&oacute;n de todas las peticiones
 * 
 * Asiste al sistema asimilando todas las peticiones y ayudando a la gesti&oacute;n de 
 * las mismas entre los diferentes controladores.
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see AccountController
 * @see PotentialClientController
 * @see OperationController
 *
 */
@Controller
@RequestMapping("*")
public class SystemController {
	
	/**
	 * Inyecci&oacute;n de dependencias: Servicio de la tabla Usuarios
	 */
	@Autowired
	private PotentialClientServiceI pClientServiceI;
	
	/**
	 * Inyecci&oacute;n de dependencias: Servicio de la tabla Cuentas
	 */
	@Autowired
	private AccountServiceI accountServiceI;
	
	/**
	 * M&eacute;todo de mapeo del index de la aplicaci&oacute;n
	 * 
	 * @return Nombre de la vista a mostrar
	 */
	@GetMapping
	public String showIndex() {
		return "index";
	}
	
	/**
	 * M&eacute;todo de mapeo para la vista: listado de clientes potenciales
	 * 
	 * @return Nombre de la vista a mostrar: el m&eacute;todo {@link PotentialClientController#showPotentialClients(Model)}
	 */
	@GetMapping("/clientsView")
	public String redirectToClientsController() {
		return "redirect:showPotentialClientsView";
	}
	
	/**
	 * M&eacute;todo de mapeo para la vista: formulario de a&ntilde;adir clientes
	 * 
	 * @return Nombre de la vista a mostrar: el m&eacute;todo {@link PotentialClientController#addNewPotentialClient(PotentialClient, BindingResult)}
	 */
	@GetMapping("/newClientView")
	public String showNewClientForm() {
		return "newClient";
	}
	
	/**
	 * M&eacute;todo de mapeo para la vista: formulario de actualizar clientes
	 * 
	 * @param pClientId Long identificador del cliente a actualizar
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar
	 */
	@PostMapping("/updateClientView")
	public String updateClientForm(@RequestParam Long pClientId, Model model) {
		model.addAttribute("myClient", pClientServiceI.getById(pClientId));
		return "updateClient";
	}
	
	/**
	 * M&eacute;todo de mapeo para la vista: formulario de buscar clientes
	 * 
	 * @return Nombre de la vista a mostrar: el m&eacute;todo {@link PotentialClientController#submitSearchClientForm(PotentialClient, Model)}
	 */
	@GetMapping("/searchClientByView")
	public String showClientSearchForm() {
		return "searchClientBy";
	}
	
	/**
	 * M&eacute;todo de mapeo para la vista: listado de cuentas bancarias
	 * 
	 * @return Nombre de la vista a mostrar: el m&eacute;todo {@link AccountController#showAccounts(Model)}
	 */
	@GetMapping("/accountsView")
	public String redirectToAccountsController() {
		return "redirect:showAccountsView";
	}
	
	
	/**
	 * M&eacute;todo de mapeo para la vista: formulario para a&ntilde;adir nuevas cuentas bancarias
	 * 
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar
	 */
	@GetMapping("/newAccountView")
	public String showNewAccountForm(Model model) {
		model.addAttribute("myPClients", pClientServiceI.findAllPotentialClient());
		return "newAccount";
	}
	
	/**
	 * M&eacute;todo de mapeo para la vista: formulario para actualizar cuentas bancarias
	 * 
	 * Requiere una lista de los due&ntilde;os de la cuenta y de todos los clientes potenciales para poder
	 * ser actualizado el campo myOwners.
	 * 
	 * @param accId Long identificador de la cuenta bancaria a actualizar
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar: el m&eacute;todo {@link AccountController#updateAccount(AccountModel, BindingResult)}
	 */
	@PostMapping("/updateAccountView")
	public String updateAccountForm(@RequestParam Long accId, Model model) {
		model.addAttribute("myAcc", accountServiceI.getById(accId));
		model.addAttribute("pClients", pClientServiceI.findAllPotentialClient());
		model.addAttribute("myPClients", pClientServiceI.findPotentialClientsById(accountServiceI.findPotentialClientsIdById(accId)));
		return "updateAccount";
	}
	
	/**
	 * M&eacute;todo de mapeo para la vista: formulario buscar cuentas bancarias
	 * 
	 * @return Nombre de la vista a mostrar: el m&eacute;todo {@link AccountController#submitSearchAccountForm(Account, Model)}
	 */
	@GetMapping("/searchAccountByView")
	public String showAccountSearchForm() {
		return "searchAccountBy";
	}
	
	/**
	 * M&eacute;todo de mapeo para la vista: listado de todas las operaciones
	 * 
	 * @return Nombre de la vista a mostrar: el m&eacute;todo {@link OperationController#showOperations(Model)}
	 */
	@GetMapping("/operationsView")
	public String redirectToOperationsController() {
		return "redirect:showOperationsView";
	}
	

	/**
	 * M&eacute;todo de mapeo para la vista: listado de clientes potenciales titulares de cuentas bancarias
	 * 
	 * Desde la misma, seg&uacute;n el cliente potencial seleccionado, podr&aacute;n realizarse las operaciones de retirada, dep&oacute;sito y transferencia
	 * 
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar
	 */
	@GetMapping("/newDepositWithdrawalView")
	public String showNewOperationForm(Model model) {
		model.addAttribute("pClientsOwners", pClientServiceI.findPotentialClientsById(pClientServiceI.findPotentialClientsOwnersId()));
		return "newDepositWithdrawal";
	}
	
	/**
	 * M&eacute;todo de mapeo para la vista: formulario para dep&oacute;sitos
	 * 
	 * @param pClientId Long identificador del cliente potencial que realiza la operaci&oacute;n
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar
	 */
	@PostMapping("/newDepositView")
	public String newDepositForm(@RequestParam Long pClientId, Model model) {
		model.addAttribute("myPClient", pClientServiceI.getById(pClientId));
		model.addAttribute("clientAccs", accountServiceI.findAccountsById(pClientServiceI.findAccountsIdById(pClientId)));
		return "newDeposit";
	}
	
	/**
	 * M&eacute;todo de mapeo para la vista: formulario para retiradas a cuenta
	 * 
	 * @param pClientId Long identificador del cliente potencial que realiza la operaci&oacute;n
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar
	 */
	@PostMapping("/newWithdrawalView")
	public String newWithdrawalForm(@RequestParam Long pClientId, Model model) {
		model.addAttribute("myPClient", pClientServiceI.getById(pClientId));
		model.addAttribute("clientAccs", accountServiceI.findAccountsById(pClientServiceI.findAccountsIdById(pClientId)));
		return "newWithdrawal";
	}
	
	
	/**
	 * M&eacute;todo de mapeo para la vista: formulario para transferencias entre cuentas bancarias
	 * 
	 * @param pClientId Long identificador del cliente potencial que realiza la operaci&oacute;n
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar
	 */
	@PostMapping("/newTransferView")
	public String newTransferForm(@RequestParam Long pClientId, Model model) {
		model.addAttribute("myPClient", pClientServiceI.getById(pClientId));
		model.addAttribute("clientAccs", accountServiceI.findAccountsById(pClientServiceI.findAccountsIdById(pClientId)));
		model.addAttribute("myAccounts", accountServiceI.findAllAccount());
//		//Listado de las cuentas de las que NO es dueñ;o
//		model.addAttribute("myAccounts", accountServiceI.findAccountsIdNotOwnedById(pClientServiceI.findAccountsIdById(pClientId)));
		return "newTransfer";
	}

}
