package com.bizitglobal.tarjetafiel.operador.exeption;

/**
 * Lanzada cuando el rol no existe en la base de datos.
 * @author Daniel
 */
public class RolNotFoundException extends RolException {
	private static final long serialVersionUID = -3851705256188805810L;

	/**
	 * Constructor con argumentos para la excepción
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public RolNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
