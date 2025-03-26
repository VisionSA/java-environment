package com.bizitglobal.tarjetafiel.impuestos.exception;

/**
 * Lanzada cuando el individuo no existe en la base de datos.
 * @author Daniel
 */
public class IndividuoNotFoundException extends IndividuoException {
	private static final long serialVersionUID = -8268157569413364731L;

	/**
	 * Constructor con argumentos para la excepción
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public IndividuoNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
