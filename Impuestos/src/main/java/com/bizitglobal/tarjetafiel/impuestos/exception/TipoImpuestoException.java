package com.bizitglobal.tarjetafiel.impuestos.exception;
/**
*	Padre de las excepciones que ocurren en la clase.
*/
public class TipoImpuestoException extends Exception {
	/**
	* Constructor por defecto de la excepción.
	* @param msg, Mensaje descriptivo de la excepción.
	*/
	public TipoImpuestoException (String msg) {

		super(msg);
	}
	/**
	* Constructor con argumentos para la excepción.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa raiz de la excepción.
	*/
	public TipoImpuestoException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

