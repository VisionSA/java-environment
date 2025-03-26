package com.bizitglobal.tarjetafiel.impuestos.exception;

/**
 * Padre de las excepciones que ocurren en tramos retencion.
 * @author Daniel
 */
public class TramosRetencionException extends Exception {
	
	/**
	 * Constructor por defecto de la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 */
	public TramosRetencionException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa raiz de la excepción.
	 */
	public TramosRetencionException(String msg,Throwable cause) {
		super(msg,cause);
	}	

}
