package com.dam.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import com.dam.springboot.entities.Account;
import com.dam.springboot.entities.PotentialClient;
import com.dam.springboot.services.AccountServiceI;
import com.dam.springboot.services.PotentialClientServiceI;

/**
 * Clase de nuestra aplicaci&oacute;n
 * 
 * Lanza nuestra aplicaci&oacute;n.
 * CommandLineRunner permite sacar por consola informaci&oacute;n, lo ejecutado en el Run().
 * Normalmente no se utilizar&iacute;a.
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 01/03/2022
 *
 */
@SpringBootApplication
public class HayounCaballeroSelmaPspHlcUt6Tarea6Application implements CommandLineRunner{

	/** Servicio: gesti&oacute;n de clientes potenciales.
	 * Si tuvieramos varias clases de implementaci&oacute;n de una interfaz
	 * Utilizamos la etiqueta @Qualify(nombreDeLaClase) */
	@Autowired
	private PotentialClientServiceI potentialClientServiceI;
	
	/**
	 * Servicio de acceso y gesti&oacute;n de cuentas de clientes
	 */
	@Autowired
	private AccountServiceI accServiceI;
	
	
	/**
	 * M&eacute;todo principal de lanzamiento de la aplicaci&oacute;n
	 * 
	 * Lanza el run del hilo principal de la aplicaci&oacute;n.
	 * 
	 * @param args Argumentos de entrada
	 */
	public static void main(String[] args) {
		SpringApplication.run(HayounCaballeroSelmaPspHlcUt6Tarea6Application.class, args);
	}

	/**
	 * M&eacute;todo run de la aplicaci&oacute;n
	 * 
	 * Hilo principal de ejecuci&oacute;n del proyecto.
	 * Lo hemos utilizado para testeo y verificaci&oacute;n en los primeros pasos de desarollo de la aplicaci&oacute;n.
	 */
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("--------------------------");
		
		//Obtención e iteración de elementos
		List<PotentialClient> cltList = potentialClientServiceI.findAllPotentialClient();
		
		if(!CollectionUtils.isEmpty(cltList)) {
			for(PotentialClient c : cltList) {
				System.out.println(c.toString());
			}
		}
		
		System.out.println("--------------------------");
		
		//Obtención de elementos por NIF (like)
		List<PotentialClient> cltList2 = potentialClientServiceI.findPotentialClientByNifContaining("F");
		
		if(!CollectionUtils.isEmpty(cltList2)) {
			for(PotentialClient c : cltList2) {
				System.out.println(c.toString());
			}
		}
		
		System.out.println("--------------------------");
		
		//Obtención de cliente por NIF
		PotentialClient pclt = potentialClientServiceI.getPotentialClientByNif("11111111-A");
		
		System.out.println(pclt.toString());
		
		//Obtenci&oacute;n de cuentas de cliente = 3
		List<Long> accountsId = potentialClientServiceI.findAccountsIdById(3L);
		
		List<Account> clientAccounts = accServiceI.findAccountsById(accountsId);
		
		for (Account acc : clientAccounts) {
			System.out.println(acc.toString());
		}
		
//		//Eliminar cliente potencial por id
//		potentialClientServiceI.removePotentialClientById(pclt.getId());
		
//		//Añadir un cliente potencial
//		PotentialClient myTestClient = new PotentialClient();
//		myTestClient.setNif("88888888-B");
//		myTestClient.setSurname("Barasona Leon");
//		myTestClient.setName("Veronica");
//		myTestClient.setYearBirth(1978);
//		myTestClient.setAddress("Avenida de la Gloria");
//		myTestClient.setEmail("galadriel@gmail.com");
//		myTestClient.setTphno("999665577");
//		
//		potentialClientServiceI.addPotentialClient(myTestClient);
		
	}

}
