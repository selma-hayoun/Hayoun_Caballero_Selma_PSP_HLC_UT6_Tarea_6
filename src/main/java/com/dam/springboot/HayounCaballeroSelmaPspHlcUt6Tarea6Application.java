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

@SpringBootApplication
public class HayounCaballeroSelmaPspHlcUt6Tarea6Application implements CommandLineRunner{

	/** Servicio: gestión de clientes potenciales.
	 * Si tuvieramos varias clases de implementación de una interfaz
	 * Utilizamos la etiqueta @Qualify(nombreDeLaClase) */
	@Autowired
	private PotentialClientServiceI potentialClientServiceI;
	
	@Autowired
	private AccountServiceI accServiceI;
	
	
	public static void main(String[] args) {
		SpringApplication.run(HayounCaballeroSelmaPspHlcUt6Tarea6Application.class, args);
	}

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
		List<PotentialClient> cltList2 = potentialClientServiceI.findPotentialClientByNifLike("F");
		
		if(!CollectionUtils.isEmpty(cltList2)) {
			for(PotentialClient c : cltList2) {
				System.out.println(c.toString());
			}
		}
		
		System.out.println("--------------------------");
		
		//Obtención de cliente por NIF
		PotentialClient pclt = potentialClientServiceI.getPotentialClientByNif("1111A");
		
		System.out.println(pclt.toString());
		
		//Obtención de cuentas de cliente = 3
		List<Long> accountsId = potentialClientServiceI.findAccountsIdById(3L);
		
		List<Account> clientAccounts = accServiceI.findAccountsById(accountsId);
		
		for (Account acc : clientAccounts) {
			System.out.println(acc.toString());
		}
		
		//Eliminar cliente potencial por id
		potentialClientServiceI.removePotentialClientById(pclt.getId());
		
		//Añadir un cliente potencial
		PotentialClient myTestClient = new PotentialClient();
		myTestClient.setNif("8888B");
		myTestClient.setSurname("Barasona Leon");
		myTestClient.setName("Verónica");
		myTestClient.setYearBirth(1978);
		myTestClient.setAddress("Avenida de la Gloria");
		myTestClient.setEmail("galadriel@gmail.com");
		myTestClient.setTphno("999665577");
		
		potentialClientServiceI.addPotentialClient(myTestClient);
		
	}

}
