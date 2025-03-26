package com.bizitglobal.tarjetafiel.operador.exeption;

/**
 * Lanzada cuando el operador no existe en la base de datos.
 * @author Daniel
 */
public class OperadorNotFoundException extends OperadorException {
	private static final long serialVersionUID = 8035765062869265718L;

	/**
	 * Constructor con argumentos para la excepción
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public OperadorNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
