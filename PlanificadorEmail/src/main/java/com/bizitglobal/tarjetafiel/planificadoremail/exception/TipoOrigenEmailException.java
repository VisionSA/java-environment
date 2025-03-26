package com.bizitglobal.tarjetafiel.planificadoremail.exception;

public class TipoOrigenEmailException extends Exception {

	private static final long serialVersionUID = 1L;


	/**
	 * Constructor por defecto de la excepción.
	 * 
	 * @param msg
	 *            , Mensaje descriptivo de la excepción.
	 */
	public TipoOrigenEmailException(String msg) {
		super(msg);
	}


	/**
	 * Constructor con argumentos para la excepción.
	 * 
	 * @param msg
	 *            , Mensaje descriptivo de la excepción.
	 * @param cause
	 *            , Causa raiz de la excepción.
	 */
	public TipoOrigenEmailException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
