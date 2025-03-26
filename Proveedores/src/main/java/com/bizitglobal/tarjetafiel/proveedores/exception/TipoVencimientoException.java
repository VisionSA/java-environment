package com.bizitglobal.tarjetafiel.proveedores.exception;

/**
 * Padre de las excepciones que ocurren en tipo vencimientos.
 * @author Daniel
 */
public class TipoVencimientoException extends Exception {
	
	/**
	 * Constructor por defecto de la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 */
	public TipoVencimientoException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa raiz de la excepción.
	 */
	public TipoVencimientoException(String msg,Throwable cause) {
		super(msg,cause);
	}	

}
