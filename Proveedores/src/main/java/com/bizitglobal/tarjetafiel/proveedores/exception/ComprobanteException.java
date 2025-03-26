package com.bizitglobal.tarjetafiel.proveedores.exception;

/**
 * Padre de las excepciones que ocurren en comprobantes.
 * @author Daniel
 */
public class ComprobanteException extends Exception {
	
	/**
	 * Constructor por defecto de la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 */
	public ComprobanteException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa raiz de la excepción.
	 */
	public ComprobanteException(String msg,Throwable cause) {
		super(msg,cause);
	}	

}
