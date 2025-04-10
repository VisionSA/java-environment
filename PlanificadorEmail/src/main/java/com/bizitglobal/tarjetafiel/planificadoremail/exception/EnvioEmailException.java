package com.bizitglobal.tarjetafiel.planificadoremail.exception;

/***** @Id:6958 ******/
public class EnvioEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3112475345453194641L;


	/**
	 * Constructor por defecto de la excepción.
	 * 
	 * @param msg
	 *            , Mensaje descriptivo de la excepción.
	 */
	public EnvioEmailException(String msg) {
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
	public EnvioEmailException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
