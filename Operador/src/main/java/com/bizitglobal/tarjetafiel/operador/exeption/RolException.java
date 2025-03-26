package com.bizitglobal.tarjetafiel.operador.exeption;

/**
 * Padre de las excepciones que ocurren en roles.
 * @author Daniel
 */
public class RolException extends Exception {
	private static final long serialVersionUID = 8018127160478653247L;

	/**
	 * Constructor por defecto de la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 */
	public RolException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa raiz de la excepción.
	 */
	public RolException(String msg,Throwable cause) {
		super(msg,cause);
	}	

}
