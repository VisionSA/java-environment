package com.bizitglobal.tarjetafiel.operador.exeption;

/**
 * Lanzada cuando el permiso no existe en la base de datos.
 * @author Daniel
 */
public class MenuItemNotFoundException extends MenuItemException {
	private static final long serialVersionUID = 8035765062869265718L;

	/**
	 * Constructor con argumentos para la excepción
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public MenuItemNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
