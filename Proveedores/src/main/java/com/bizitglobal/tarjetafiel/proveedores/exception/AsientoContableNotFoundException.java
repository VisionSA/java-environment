package com.bizitglobal.tarjetafiel.proveedores.exception;

/**
 * Lanzada cuando el asiento contable no existe en la base de datos.
 * @author Daniel
 */
public class AsientoContableNotFoundException extends AsientoContableException {
	private static final long serialVersionUID = 8035765062869265718L;

	/**
	 * Constructor con argumentos para la excepción
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public AsientoContableNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
