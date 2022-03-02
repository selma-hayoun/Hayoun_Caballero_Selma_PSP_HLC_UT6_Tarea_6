package com.dam.springboot.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Clase IssariesControllerAdvice para el control y visualización de errores
 * 
 * Asiste a los diferentes controladores. Si en alguna de sus operaciones se produce
 * una excepción, se redirige por este.
 * 
 * @author Selma Hayoun Caballero
 * @version 0.1, 02/03/2022
 * @see AccountController
 * @see PotentialClientController
 * @see OperationController
 *
 */
@ControllerAdvice
public class IssariesControllerAdvice {

	/**
	 * Método manejador de excepciones de la aplicación.
	 * 
	 * Capta cualquier excepción en los métodos de cualquier controlador
	 * 
	 * @param req Objeto que contiene toda la información de la solicitud HTTP
	 * @param e Excepción producida
	 * @param model Modelo de la vista
	 * @return Nombre de la vista a mostrar
	 */
	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest req, Exception e, Model model) {
		// Respuesta
		model.addAttribute("errorMsg", e.getMessage());
		return "error";
	}
}
