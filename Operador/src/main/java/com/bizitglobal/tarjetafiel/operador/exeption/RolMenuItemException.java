package com.bizitglobal.tarjetafiel.operador.exeption;

/**
 * Padre de las excepciones que ocurren en permisos.
 * @author Daniel
 */
public class RolMenuItemException extends Exception {
	
	/**
	 * Constructor por defecto de la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 */
	public RolMenuItemException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa raiz de la excepción.
	 */
	public RolMenuItemException(String msg,Throwable cause) {
		super(msg,cause);
	}	

}
