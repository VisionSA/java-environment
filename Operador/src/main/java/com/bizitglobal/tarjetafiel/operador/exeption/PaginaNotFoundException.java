package com.bizitglobal.tarjetafiel.operador.exeption;

/**
 * Lanzada cuando el permiso no existe en la base de datos.
 * @author Daniel
 */
public class PaginaNotFoundException extends PaginaException {
	private static final long serialVersionUID = 8035765062869265718L;

	/**
	 * Constructor con argumentos para la excepción
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public PaginaNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
