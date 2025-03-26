package com.bizitglobal.tarjetafiel.fondos.exception;

public class FormatoNoValidoException extends NumberFormatException{

	private static final long serialVersionUID = 4756520197128648384L;

	/**
	 * Constructor con argumentos para la actividad.
	 * 
	 * @param msg
	 *            , Mensaje descriptivo de la excepción.
	 * @param cause
	 *            , Causa de la excepción.
	 */
	public FormatoNoValidoException(String msg) {

		super(msg);
	}
}
