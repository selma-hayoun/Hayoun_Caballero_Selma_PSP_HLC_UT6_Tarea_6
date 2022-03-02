package com.dam.springboot.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
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
import com.dam.springboot.entities.Operation;
import com.dam.springboot.models.AccountModel;
import com.dam.springboot.services.AccountServiceI;
import com.dam.springboot.services.OperationServiceI;


/**
 * Clase AccountController: controlador para la gesti&oacute;n de cuentas bancarias
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Account
 * @see AccountServiceI
 * @see Operation
 * @see OperationServiceI
 *
 */
@Controller
public class AccountController {

	/**
	 * Inyecci&oacute;n de dependencias: Servicio de la tabla Cuentas
	 */
	@Autowired
	private AccountServiceI accServiceI;
	
	/**
	 * Inyecci&oacute;n de dependencias: Servicio de la tabla Operaciones
	 */
	@Autowired
	private OperationServiceI opServiceI;
	
	/**
	 * M&eacute;todo para mostrar la vista showAccounts con todas las cuentas bancarias
	 * 
	 * @param model Modelo de la vista al que a&ntilde;adimos como atributos la lista y el bot&oacute;n
	 * @return Nombre de la vista a mostrar
	 */
	@GetMapping("/showAccountsView")
	public String showAccounts(Model model) {
		// Obtención de las cuentas
		List<Account> accList = accServiceI.findAllAccount();
		
		// Carga de datos al modelo: crear clave valor
		model.addAttribute("accListView", accList);
		model.addAttribute("btnDropAccEnabled", Boolean.FALSE);
		
		return "showAccounts";
	}

	/**
	 * M&eacute;todo de la acci&oacute;n de eliminar una cuenta
	 * 
	 * @param accId Identificador &uacute;nico de la cuenta bancaria (ID)
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar: redirige al m&eacute;todo {@link #showAccounts(Model)}
	 */
	@PostMapping("/actDropAccount")
	public String removeAccount(@RequestParam String accId, Model model) {
		// Eliminación de Cuenta
		accServiceI.removeAccountById(Long.valueOf(accId));		
		return "redirect:showAccountsView";
	}
	
	/**
	 * M&eacute;todo para la acci&oacute;n de mostrar b&uacute;squeda de cuenta bancaria por los par&aacute;metros del objeto 
	 * Account recibido.
	 * 
	 * A la vista de listar cuentas bancarias le a&ntilde;ade a su modelo la lista resultante de buscar
	 * los par&aacute;metros introducidos por el usuario.
	 * 
	 * @param searchedAccount Objeto Account mapeado por la vista con los par&aacute;mentros introducidos por el usuario
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar
	 * @throws Exception Captura las posibles excepciones de mapeo y extracci&oacute;n de datos
	 */
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

	/**
	 * M&eacute;todo para la acci&oacute;n de a&ntilde;adir una cuenta bancaria nueva.
	 * 
	 * @param newAccountModel Objeto AccountModel mapeado por la vista
	 * @param result Analiza el resultado de la operaci&oacute;n de lo devuelto por la vista, nos sirve para saber si ha habido errores en el mapeo
	 * @return Nombre de la vista a mostrar: redirige al m&eacute;todo {@link #showAccounts(Model)}
	 * @throws Exception Captura las posibles excepciones de mapeo y extracci&oacute;n de datos
	 */
	@PostMapping("/actAddAccount")
	private String addNewAccount(@Valid @ModelAttribute AccountModel newAccountModel, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			if(accServiceI.getAccountByNumAccount(newAccountModel.getNumAccount()) != null) {
				throw new Exception("Ya existe un cuenta bancaria dada de alta con ese n&uacute;mero de cuenta.");
			} else {
				throw new Exception("Parámetros de búsqueda erróneos.");
			}			
		} else {
			Account newAccount = new Account();
			newAccount.setNumAccount(newAccountModel.getNumAccount());
			// Creando un objeto LocalDatetime
			LocalDate currentLocalDate = LocalDate.now();		
			// Tomamo el timezone del sistema
			ZoneId systemTimeZone = ZoneId.systemDefault();		
			// Convertimos el ocalDateTime a ZonedDateTime con el timezone
			ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);		
			// Convertimos ZonedDateTime a Date
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
			
			//Hacer insert en la tabla relacional id cuenta e id cliente
			//Necesitamos saber el id
			Long idAccount = accServiceI.getAccountByNumAccount(newAccountModel.getNumAccount()).getId();
			for(Long idClient : newAccountModel.getMyOwners()) {
				accServiceI.addClientAccountReg(idClient, idAccount);
			}			
		}
		return "redirect:showAccountsView";
	}
	
	/**
	 * M&eacute;todo de la acci&oacute;n de actualizar una cuenta bancaria del sistema.
	 * 
	 * Utilizamos un objeto AccountModel para recopilar los datos del formulario y con
	 * los mismos construir nuestro objeto Account.
	 * 
	 * @param acc Objeto AccountModel mapeado por la vista
	 * @param result Analiza el resultado de la operaci&oacute;n de lo devuelto por la vista, nos sirve para saber si ha habido errores en el mapeo
	 * @return Nombre de la vista a mostrar: redirige al m&eacute;todo {@link #showAccounts(Model)}
	 * @throws Exception Captura las posibles excepciones de mapeo y extracci&oacute;n de datos
	 */
	@PostMapping("/actUpdateAccount")
	private String updateAccount(@Valid @ModelAttribute AccountModel acc, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			if(accServiceI.getAccountByNumAccount(acc.getNumAccount()) != null) {
				throw new Exception("Ya existe un cuenta bancaria dada de alta con ese n&uacute;mero de cuenta.");
			} else {
				throw new Exception("Parámetros de búsqueda erróneos.");
			}
		} else {
			Account account = accServiceI.getById(acc.getId());
			
			//Seteamos los campos con los datos del modelo
			account.setNumAccount(acc.getNumAccount());
			double balance;
			try {
				balance = Double.parseDouble(acc.getBalance());
			} catch (Exception e) {
				balance = 0;
			}
			account.setBalance(balance);
			
			accServiceI.updateAccount(account);
			
			//Debemos actualizar tabla relacional id cuenta e id cliente
			//Eliminamos los anteriores
			accServiceI.deleteClientAccountReg(acc.getId());
			
			//Insertamos los clientes asignados
			for(Long idClient : acc.getMyOwners()) {
				accServiceI.addClientAccountReg(idClient, acc.getId());
			}		
			
		}
		return "redirect:showAccountsView";
	}
	
	/**
	 * M&eacute;todo de la acci&oacute;n de mostrar las operaciones de una determinada cuenta bancaria por ID
	 * 
	 * @param referrer Objeto para mantener la referencia de la &uacute;ltima vista que visit&oacute; y vinvularla al bot&oacute;n volver
	 * @param accId Identificador de la cuenta bancaria cuyas operaciones deseamos visualizar
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar: redirige al m&eacute;todo OperationController#showOperations
	 */
	@PostMapping("/actOperationsAccount")
	public String showOperationsAccount(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer, @RequestParam String accId, Model model) {
		if( referrer != null ) {//Sería nulo si entra directamente a esta URL
		      model.addAttribute("previousUrl", referrer);
		}
		List<Operation> myOps = opServiceI.findOperationsByAccountId(Long.valueOf(accId));
		// Carga de datos al modelo
		model.addAttribute("opListView", myOps);
		model.addAttribute("btnDropOpEnabled", Boolean.FALSE);
		
		return "showOperations";
	}
	
}
