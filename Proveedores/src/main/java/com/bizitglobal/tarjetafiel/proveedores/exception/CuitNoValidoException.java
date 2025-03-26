package com.bizitglobal.tarjetafiel.proveedores.exception;

/**
 * Es lanzada cuando se intenta crear un cuit que no es valido.
 * @author Daniel
 */
public class CuitNoValidoException extends Exception {
	
	/**
	 * Constructor por defecto de la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 */
	public CuitNoValidoException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa raiz de la excepción.
	 */
	public CuitNoValidoException(String msg,Throwable cause) {
		super(msg,cause);
	}	

}
