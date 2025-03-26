package com.bizitglobal.tarjetafiel.proveedores.exception;

/**
 * Padre de las excepciones que ocurren en asiento contables.
 * @author Daniel
 */
public class AsientoContableException extends Exception {
	
	/**
	 * Constructor por defecto de la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 */
	public AsientoContableException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa raiz de la excepción.
	 */
	public AsientoContableException(String msg,Throwable cause) {
		super(msg,cause);
	}	

}
