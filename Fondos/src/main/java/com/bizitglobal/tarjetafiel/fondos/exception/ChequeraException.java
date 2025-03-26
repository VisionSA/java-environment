package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Padre de las excepciones que ocurren en la clase.
*/
public class ChequeraException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3710400049596284795L;
	/**
	* Constructor por defecto de la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	*/
	public ChequeraException (String msg) {

		super(msg);
	}
	/**
	* Constructor con argumentos para la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa raiz de la excepcion.
	*/
	public ChequeraException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

