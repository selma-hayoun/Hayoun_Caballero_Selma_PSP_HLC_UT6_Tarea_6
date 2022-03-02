package com.dam.springboot.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dam.springboot.entities.Account;
import com.dam.springboot.entities.Operation;
import com.dam.springboot.entities.OperationType;
import com.dam.springboot.models.OperationModel;
import com.dam.springboot.services.AccountServiceI;
import com.dam.springboot.services.OperationServiceI;

/**
 * Clase OperationController: controlador para la gesti&oacute;n de operaciones
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Operation
 * @see OperationServiceI
 * @see Account
 * @see AccountServiceI
 *
 */
@Controller
public class OperationController {

	/**
	 * Inyecci&oacute;n de dependencias: Servicio de la tabla Operaciones
	 */
	@Autowired
	private OperationServiceI opServiceI;
	
	/**
	 * Inyecci&oacute;n de dependencias: Servicio de la tabla Cuentas
	 */
	@Autowired
	private AccountServiceI accServiceI;
	
	/**
	 * M&eacute;todo para mostrar la vista showOperations con todas las operaciones ordenadas por fecha
	 * (las m&aacute;s actuales primero)
	 * 
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar
	 */
	@GetMapping("/showOperationsView")
	public String showOperations(Model model) {
//		// Obtención de todas las operaciones ordenadas por id
//		List<Operation> opList = opServiceI.findAllOperation();
		
		//Obtención de todas las operaciones ordenadas por fecha
		List<Operation> opList = opServiceI.findOperationsOrderByDate();
		
		// Carga de datos al modelo: crear clave valor
		model.addAttribute("opListView", opList);
		model.addAttribute("btnDropOpEnabled", Boolean.FALSE);
		
		return "showOperations";
	}
	
	/**
	 * M&eacute;todo para la acci&oacute;n de realizar un dep&oacute;sito en una cuenta determinada de un cliente potencial concreto
	 * 
	 * Utilizamos el modelo OperationModel para recopilar los datos del formulario y con los
	 * mismos construir nuestro objeto Operation. La fecha se registra la del momento de la operaci&oacute;n.
	 * 
	 * @param newOpModel Objeto OperationModel mapeado por la vista
	 * @param result Analiza el resultado de la operaci&oacute;n de lo devuelto por la vista, nos sirve para saber si ha habido errores en el mapeo
	 * @return Nombre de la vista a mostrar: redirige al m&eacute;todo {@link #showOperations(Model)}
	 * @throws Exception Captura las posibles excepciones de mapeo y extracci&oacute;n de datos
	 */
	@PostMapping("/actDeposit")
	private String addDeposit(@Valid @ModelAttribute OperationModel newOpModel, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de búsqueda erróneos.");			
		} else {
			Operation newOp = new Operation();			
			
			// Creando un objeto LocalDatetime
			LocalDate currentLocalDate = LocalDate.now();		
			// Tomamo el timezone del sistema
			ZoneId systemTimeZone = ZoneId.systemDefault();		
			// Convertimos el ocalDateTime a ZonedDateTime con el timezone
			ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);		
			// Convertimos ZonedDateTime a Date
			Date utilDate = Date.from(zonedDateTime.toInstant());
			
			// Seteamos la fecha actual
			newOp.setCreateAt(utilDate);
			
			//Seteamos el tipo de operación
			newOp.setOp(OperationType.DEPOSIT);
			
			double amount;
			try {
				amount = Double.parseDouble(newOpModel.getAmount());
			} catch (Exception e) {
				amount = 0;
			}
			
			//Seteamos la cantidad
			newOp.setAmount(amount);
			
			//Seteamos el id de la cuenta
			newOp.setAccount_id(newOpModel.getAccountId());
			
			System.out.println(newOp.toString());
			System.out.println(newOpModel.toString());
			
			// Se a&ntilde;ade la nueva operación
			opServiceI.addOperation(newOp);	
			
			//Se actualiza el balance de la cuenta
			Account acc = accServiceI.getById(newOpModel.getAccountId());
			acc.setBalance(acc.getBalance() + newOp.getAmount());
			accServiceI.updateAccount(acc);
						
		}
		return "redirect:showOperationsView";
	}
	
	/**
	 * M&eacute;todo para la acci&oacute;n de realizar una retirada de una cuenta de un cliente potencial concreto
	 * 
	 * Utilizamos el modelo OperationModel para recopilar los datos del formulario y con los
	 * mismos construir nuestro objeto Operation. La fecha se registra la del momento de la operaci&oacute;n.
	 * 
	 * @param newOpModel Objeto OperationModel mapeado por la vista
	 * @param result Analiza el resultado de la operaci&oacute;n de lo devuelto por la vista, nos sirve para saber si ha habido errores en el mapeo
	 * @return Nombre de la vista a mostrar: redirige al m&eacute;todo {@link #showOperations(Model)}
	 * @throws Exception Captura las posibles excepciones de mapeo y extracci&oacute;n de datos
	 */
	@PostMapping("/actWithdrawal")
	private String addWithdrawal(@Valid @ModelAttribute OperationModel newOpModel, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de búsqueda erróneos.");			
		} else {
			//Lo primero de todo es comprobar que tiene saldo
			//Si no lo tuviera cancelamos la operación
			double amount;
			try {
				amount = Double.parseDouble(newOpModel.getAmount());
			} catch (Exception e) {
				amount = 0;
			}
			
			if(accServiceI.getById(newOpModel.getAccountId()).getBalance() < amount) {
				throw new Exception("Operación cancelada: no tiene suficiente saldo en su cuenta para retirar la cantidad solicitada.");
			} else {
				Operation newOp = new Operation();			
				
				// Creando un objeto LocalDatetime
				LocalDate currentLocalDate = LocalDate.now();		
				// Tomamo el timezone del sistema
				ZoneId systemTimeZone = ZoneId.systemDefault();		
				// Convertimos el ocalDateTime a ZonedDateTime con el timezone
				ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);		
				// Convertimos ZonedDateTime a Date
				Date utilDate = Date.from(zonedDateTime.toInstant());
				
				// Seteamos la fecha actual
				newOp.setCreateAt(utilDate);
				
				//Seteamos el tipo de operación
				newOp.setOp(OperationType.WITHDRAWAL);					
				
				//Seteamos la cantidad
				newOp.setAmount(amount);
				
				//Seteamos el id de la cuenta
				newOp.setAccount_id(newOpModel.getAccountId());
				
				System.out.println(newOp.toString());
				System.out.println(newOpModel.toString());
				
				// Se a&ntilde;ade la nueva operación
				opServiceI.addOperation(newOp);	
				
				//Se actualiza el balance de la cuenta
				Account acc = accServiceI.getById(newOpModel.getAccountId());
				acc.setBalance(acc.getBalance() - newOp.getAmount());
				accServiceI.updateAccount(acc);				
			}		
						
		}
		return "redirect:showOperationsView";
	}
	
	/**
	 * M&eacute;todo para la acci&oacute;n de realizar una transferencia de una cuenta de un cliente potencial concreto
	 * a otra cuenta bancaria del sistema
	 * 
	 * Utilizamos el modelo OperationModel para recopilar los datos del formulario y con los
	 * mismos construir dos objetos Operation (emisi&oacute;n y recepci&oacute;n). 
	 * La fecha se registra la del momento de la operaci&oacute;n.
	 * 
	 * @param newOpModel Objeto OperationModel mapeado por la vista
	 * @param result Analiza el resultado de la operaci&oacute;n de lo devuelto por la vista, nos sirve para saber si ha habido errores en el mapeo
	 * @return Nombre de la vista a mostrar: redirige al m&eacute;todo {@link #showOperations(Model)}
	 * @throws Exception Captura las posibles excepciones de mapeo y extracci&oacute;n de datos
	 */
	@PostMapping("/actTransfer")
	private String addTransfer(@Valid @ModelAttribute OperationModel newOpModel, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de búsqueda erróneos.");			
		} else {
			//Lo primero de todo es comprobar que tiene saldo y la cuenta de origen y destino son diferentes
			double amount;
			try {
				amount = Double.parseDouble(newOpModel.getAmount());
			} catch (Exception e) {
				amount = 0;
			}
			
			if(accServiceI.getById(newOpModel.getAccountId()).getBalance() < amount) {
				throw new Exception("Operación cancelada: no tiene suficiente saldo en su cuenta para retirar la cantidad solicitada.");
			} else if(newOpModel.getAccountId() == newOpModel.getAccountIdTo()) {
				throw new Exception("Operación cancelada: la cuenta origen y destino no pueden ser la misma.");
			}else {
				Operation newOpOrigin = new Operation();	
				Operation newOpDestiny = new Operation();
				
				// Creando un objeto LocalDatetime
				LocalDate currentLocalDate = LocalDate.now();		
				// Tomamo el timezone del sistema
				ZoneId systemTimeZone = ZoneId.systemDefault();		
				// Convertimos el ocalDateTime a ZonedDateTime con el timezone
				ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);		
				// Convertimos ZonedDateTime a Date
				Date utilDate = Date.from(zonedDateTime.toInstant());
				
				// Seteamos la fecha actual
				newOpOrigin.setCreateAt(utilDate);
				newOpDestiny.setCreateAt(utilDate);
				
				//Seteamos el tipo de operación
				newOpOrigin.setOp(OperationType.ISSUED_TRANSFER);	
				newOpDestiny.setOp(OperationType.RECEIVED_TRANSFER);
				
				//Seteamos la cantidad
				newOpOrigin.setAmount(amount);
				newOpDestiny.setAmount(amount);
				
				//Seteamos el id de la cuenta
				newOpOrigin.setAccount_id(newOpModel.getAccountId());
				newOpDestiny.setAccount_id(newOpModel.getAccountIdTo());
				
				//Salidas por consola de control
				System.out.println("Operacion de origen: " + newOpOrigin.toString());
				System.out.println("Operacion de destino: " + newOpOrigin.toString());
				System.out.println("Operacion recibida del modelo: " + newOpModel.toString());
				
				// Se añade la nueva operaci&oacute;n
				opServiceI.addOperation(newOpOrigin);	
				opServiceI.addOperation(newOpDestiny);	
				
				//Se actualiza el balance de la cuenta origen
				Account acc = accServiceI.getById(newOpModel.getAccountId());
				acc.setBalance(acc.getBalance() - newOpOrigin.getAmount());
				accServiceI.updateAccount(acc);		
				
				//Y la cuenta de destino
				Account accTo = accServiceI.getById(newOpModel.getAccountIdTo());
				accTo.setBalance(accTo.getBalance() + newOpOrigin.getAmount());
				accServiceI.updateAccount(accTo);	
			}		
						
		}
		return "redirect:showOperationsView";
	}
}
