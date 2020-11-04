package com.Analisis2.Exception;

public class UsernameOrIdNotFound extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1379927378785044031L;
	/**
	 * 
	 */
	
	public UsernameOrIdNotFound() {
		
		
		super("Usuario o Id no encontrado");
	}
	
	public UsernameOrIdNotFound(String message) {
		
		super(message);
	}
	

}
