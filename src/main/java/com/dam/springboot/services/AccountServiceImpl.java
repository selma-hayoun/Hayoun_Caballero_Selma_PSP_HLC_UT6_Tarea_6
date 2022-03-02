package com.dam.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam.springboot.entities.Account;
import com.dam.springboot.repositories.AccountRepository;

/**
 * Clase AccountServiceImpl: Implementa los m&eacute;todos de la interfaz de servicio AccountServiceI
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
	 * Inyecci&oacute;n de dependencias: Repositorio de la tabla Cuentas
	 */
	@Autowired
	private AccountRepository accRepository;

	/**
	 * Implementaci&oacute;n del m&eacute;todo para listar todas las cuentas bancarias
	 * 
	 * @return Listado con todos los objetos Account de la tabla Cuentas
	 */
	@Override
	public List<Account> findAllAccount() {
		return accRepository.findAll();
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer un objeto Account seg&uacute;n un n&uacute;mero de cuenta dado
	 * 
	 * @param numAccount Cadena de caracteres n&uacute;mero de cuenta &uacute;nico
	 * @return Objeto Account cuyo su n&uacute;mero de cuenta es el enviado como par&aacute;metro
	 */
	@Override
	public Account getAccountByNumAccount(String numAccount) {
		return (Account) accRepository.getByNumAccount(numAccount);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para a&ntilde;adir una cuenta bancaria al sistema
	 * 
	 * @param acc Objeto Account que se quiere insertar en la tabla Cuentas
	 */
	@Override
	public void addAccount(Account acc) {
		accRepository.save(acc);		
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para eliminar una cuenta bancaria por su ID
	 * 
	 * @param IdAccount Long identificador &uacute;nico de la cuenta
	 */
	@Override
	public void removeAccountById(long IdAccount) {
		accRepository.deleteById(IdAccount);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para actualizar los datos de una cuenta del sistema
	 * 
	 * @param acc Objeto Account con los datos actualizados que se desea mergear
	 */
	@Override
	public void updateAccount(Account acc) {
		accRepository.save(acc);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer un listado de cuentas del sistema seg&uacute;n un listado de IDs
	 * 
	 * @param accIds Listado de Long, identificadores &uacute;nicos de la cuentas
	 * @return Listado de objetos Account cuyos IDs se encuentran en el listado proporcionado como par&aacute;metro
	 */
	@Override
	public List<Account> findAccountsById(List<Long> accIds) {
		return accRepository.findAllById(accIds);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer un objeto Account por su ID
	 * 
	 * @param id Long identificador &uacute;nico de la cuenta
	 * @return Objeto Account (Cuenta bancaria) cuyo ID es el proporcionado como par&aacute;metro
	 */
	@Override
	public Account getById(Long id) {
		return accRepository.getById(id);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para extraer un listado de cuentas bancarias por n&uacute;mero de cuenta
	 * 
	 * Devuelve todas las cuentas bancarias del sistema cuya n&uacute;mero de cuenta contiene la cadena
	 * de caract&eacute;res aportada por par&aacute;metro.
	 * 
	 * @param numAccount Cadena de caracteres con n&uacute;mero de cuenta &uacute;nico aproximado
	 * @return Objeto Account cuyo su n&uacute;mero de cuenta contiene el enviado como par&aacute;metro
	 */
	@Override
	public List<Account> findByNumAccountContaining(String numAccount) {
		return accRepository.findByNumAccountContaining(numAccount);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para inserta registros en la tabla relacional N:M client_account
	 * 
	 * Permite registrar las tuplas de relaci&oacute;n entre PotentialClient y Account cuando se da
	 * de alta o modifican los datos de una cuenta bancaria
	 * 
	 * @param idClient Id del cliente due&ntilde;o de la cuenta
	 * @param idAccount Id de la cuenta bancaria
	 */
	@Override
	public void addClientAccountReg(Long idClient, Long idAccount) {
		accRepository.addClientAccountReg(idClient, idAccount);	
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para listar los ID de los clientes due&ntilde;os de una cuenta bancaria espec&iacute;fica
	 * 
	 * Permite sacar el listado de identificadores &uacute;nico de los PotentialClients vinculados a
	 * un ID concreto de cuenta bancaria.
	 * 
	 * @param id ID de la cuenta bancaria
	 * @return Listado de IDs (Long) de los clientes due&ntilde;os de la cuenta proporcionada como par&aacute;metro
	 */
	@Override
	public List<Long> findPotentialClientsIdById(Long id) {
		return accRepository.findPotentialClientsIdById(id);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para borrar registros en la tabla N:M seg&uacute;n un ID de cuenta bancaria
	 * 
	 * @param id Identificador &uacute;nico de la cuenta bancaria cuyos registros se quieren eliminar
	 */
	@Override
	public void deleteClientAccountReg(Long id) {
		accRepository.deleteClientAccountReg(id);		
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para localizar las cuentas h&uacute;erfanas en nuestra tabla N:M client_account
	 * 
	 * Tras la eliminaci&oacute;n de un cliente y sus registros en la tabla relacional N:M,
	 * podr&iacute;an existir cuentas de las cuales &eacute;l fuera el &uacute;nico due&ntilde;o. Estas cuentas existir&aacute;n 
	 * en nuestra tabla Cuentas pero no en client_account. Debemos verificarlo para proceder a 
	 * la eliminaci&oacute;n manual posterior de la cuenta hu&eacute;rfana o sin due&ntilde;os.
	 * 
	 * @param id Identificador &uacute;nico de la cuenta bancaria a verificar
	 * @return Devuelve el n&uacute;mero de registros que existen en la tabla client_account con referencia al ID proporcionado
	 */
	@Override
	public Integer countOrphanAccounts(Long id) {
		return accRepository.countOrphanAccounts(id);
	}

	/**
	 * Implementaci&oacute;n del m&eacute;todo para listar las cuentas bancarias que no pertenecen a un listado de ID de clientes datos
	 * 
	 * Permite sacar un listado de objetos Account que no son pertenencientes al conjunto de IDs de clientes
	 * proporcionados como par&aacute;metro. Se utiliz&oacute; en iteraciones intermedias del programa para la implementaci&oacute;n
	 * de las vistas de actualizaci&oacute;n de la cuenta bancaria para limitar la informaci&oacute;n a visualizar.
	 * 
	 * @param idList Listado de IDs pertenecientes a un conjunto de clientes
	 * @return Listado de cuentas bancarias que no son propiedad del listado de IDs proporcionados por par&aacute;metro
	 */
	@Override
	public List<Account> findAccountsIdNotOwnedById(List<Long> idList) {
		return accRepository.findAccountsIdNotOwnedById(idList);
	}

}
