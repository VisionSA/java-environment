package com.bizitglobal.tarjetafiel.operador.exeption;

/**
 * Se lanza cuando existe un error en el logueo.
 * @author Daniel
 */
public class LoginException extends Exception {

	/**
	 * Constructor por defecto para la excepción.
	 */
	public LoginException() {
		super();
	}

	/**
	 * Constructor con argumentos.
	 * @param msg, Mensaje descriptivo para la excepción.
	 */
	public LoginException(String msg) {
		super(msg);
	}
	
}
