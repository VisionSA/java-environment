package com.bizitglobal.tarjetafiel.proveedores.exception;

/**
 * Es lanzada cuando se intenta crear un numero dgr que no es valido.
 * @author Daniel
 */
public class DgrNoValidoException extends Exception {
	
	/**
	 * Constructor por defecto de la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 */
	public DgrNoValidoException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa raiz de la excepción.
	 */
	public DgrNoValidoException(String msg,Throwable cause) {
		super(msg,cause);
	}	

}
