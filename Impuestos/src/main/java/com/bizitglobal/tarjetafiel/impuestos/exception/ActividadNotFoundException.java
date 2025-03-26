package com.bizitglobal.tarjetafiel.impuestos.exception;

/**@deprecated
 * Lanzada cuando la categoria no existe en la base de datos.
 * @author Daniel
 */
public class ActividadNotFoundException extends ActividadException {
	private static final long serialVersionUID = -8268157569413364731L;

	/**
	 * Constructor con argumentos para la excepción
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public ActividadNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
