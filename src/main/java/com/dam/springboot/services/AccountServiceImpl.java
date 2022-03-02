package com.dam.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam.springboot.entities.Account;
import com.dam.springboot.repositories.AccountRepository;

/**
 * Clase AccountServiceImpl: Implementa los métodos de la interfaz de servicio AccountServiceI
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see Account
 * @see AccountServiceI
 *
 */
@Service
public class AccountServiceImpl implements AccountServiceI {
	
	/**
	 * Inyección de dependencias: Repositorio de la tabla Cuentas
	 */
	@Autowired
	private AccountRepository accRepository;

	/**
	 * Implementación del método para listar todas las cuentas bancarias
	 * 
	 * @return Listado con todos los objetos Account de la tabla Cuentas
	 */
	@Override
	public List<Account> findAllAccount() {
		return accRepository.findAll();
	}

	/**
	 * Implementación del método para extraer un objeto Account según un número de cuenta dado
	 * 
	 * @param numAccount Cadena de caracteres número de cuenta único
	 * @return Objeto Account cuyo su número de cuenta es el enviado como parámetro
	 */
	@Override
	public Account getAccountByNumAccount(String numAccount) {
		return (Account) accRepository.getByNumAccount(numAccount);
	}

	/**
	 * Implementación del método para añadir una cuenta bancaria al sistema
	 * 
	 * @param acc Objeto Account que se quiere insertar en la tabla Cuentas
	 */
	@Override
	public void addAccount(Account acc) {
		accRepository.save(acc);		
	}

	/**
	 * Implementación del método para eliminar una cuenta bancaria por su ID
	 * 
	 * @param IdAccount Long identificador único de la cuenta
	 */
	@Override
	public void removeAccountById(long IdAccount) {
		accRepository.deleteById(IdAccount);
	}

	/**
	 * Implementación del método para actualizar los datos de una cuenta del sistema
	 * 
	 * @param acc Objeto Account con los datos actualizados que se desea mergear
	 */
	@Override
	public void updateAccount(Account acc) {
		accRepository.save(acc);
	}

	/**
	 * Implementación del método para extraer un listado de cuentas del sistema según un listado de IDs
	 * 
	 * @param accIds Listado de Long, identificadores únicos de la cuentas
	 * @return Listado de objetos Account cuyos IDs se encuentran en el listado proporcionado como parámetro
	 */
	@Override
	public List<Account> findAccountsById(List<Long> accIds) {
		return accRepository.findAllById(accIds);
	}

	/**
	 * Implementación del método para extraer un objeto Account por su ID
	 * 
	 * @param id Long identificador único de la cuenta
	 * @return Objeto Account (Cuenta bancaria) cuyo ID es el proporcionado como parámetro
	 */
	@Override
	public Account getById(Long id) {
		return accRepository.getById(id);
	}

	/**
	 * Implementación del método para extraer un listado de cuentas bancarias por número de cuenta
	 * 
	 * Devuelve todas las cuentas bancarias del sistema cuya número de cuenta contiene la cadena
	 * de caractéres aportada por parámetro.
	 * 
	 * @param numAccount Cadena de caracteres con número de cuenta único aproximado
	 * @return Objeto Account cuyo su número de cuenta contiene el enviado como parámetro
	 */
	@Override
	public List<Account> findByNumAccountContaining(String numAccount) {
		return accRepository.findByNumAccountContaining(numAccount);
	}

	/**
	 * Implementación del método para inserta registros en la tabla relacional N:M client_account
	 * 
	 * Permite registrar las tuplas de relación entre PotentialClient y Account cuando se da
	 * de alta o modifican los datos de una cuenta bancaria
	 * 
	 * @param idClient Id del cliente dueño de la cuenta
	 * @param idAccount Id de la cuenta bancaria
	 */
	@Override
	public void addClientAccountReg(Long idClient, Long idAccount) {
		accRepository.addClientAccountReg(idClient, idAccount);	
	}

	/**
	 * Implementación del método para listar los ID de los clientes dueños de una cuenta bancaria específica
	 * 
	 * Permite sacar el listado de identificadores único de los PotentialClients vinculados a
	 * un ID concreto de cuenta bancaria.
	 * 
	 * @param id ID de la cuenta bancaria
	 * @return Listado de IDs (Long) de los clientes dueños de la cuenta proporcionada como parámetro
	 */
	@Override
	public List<Long> findPotentialClientsIdById(Long id) {
		return accRepository.findPotentialClientsIdById(id);
	}

	/**
	 * Implementación del método para borrar registros en la tabla N:M según un ID de cuenta bancaria
	 * 
	 * @param id Identificador único de la cuenta bancaria cuyos registros se quieren eliminar
	 */
	@Override
	public void deleteClientAccountReg(Long id) {
		accRepository.deleteClientAccountReg(id);		
	}

	/**
	 * Implementación del método para localizar las cuentas húerfanas en nuestra tabla N:M client_account
	 * 
	 * Tras la eliminación de un cliente y sus registros en la tabla relacional N:M,
	 * podrían existir cuentas de las cuales él fuera el único dueño. Estas cuentas existirán 
	 * en nuestra tabla Cuentas pero no en client_account. Debemos verificarlo para proceder a 
	 * la eliminación manual posterior de la cuenta huérfana o sin dueños.
	 * 
	 * @param id Identificador único de la cuenta bancaria a verificar
	 * @return Devuelve el número de registros que existen en la tabla client_account con referencia al ID proporcionado
	 */
	@Override
	public Integer countOrphanAccounts(Long id) {
		return accRepository.countOrphanAccounts(id);
	}

	/**
	 * Implementación del método para listar las cuentas bancarias que no pertenecen a un listado de ID de clientes datos
	 * 
	 * Permite sacar un listado de objetos Account que no son pertenencientes al conjunto de IDs de clientes
	 * proporcionados como parámetro. Se utilizó en iteraciones intermedias del programa para la implementación
	 * de las vistas de actualización de la cuenta bancaria para limitar la información a visualizar.
	 * 
	 * @param idList Listado de IDs pertenecientes a un conjunto de clientes
	 * @return Listado de cuentas bancarias que no son propiedad del listado de IDs proporcionados por parámetro
	 */
	@Override
	public List<Account> findAccountsIdNotOwnedById(List<Long> idList) {
		return accRepository.findAccountsIdNotOwnedById(idList);
	}

}
