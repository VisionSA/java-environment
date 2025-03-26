package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Padre de las excepciones que ocurren en la clase.
*/
public class MovimientoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7375723273935630902L;
	/**
	* Constructor por defecto de la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	*/
	public MovimientoException (String msg) {

		super(msg);
	}
	/**
	* Constructor con argumentos para la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa raiz de la excepcion.
	*/
	public MovimientoException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

