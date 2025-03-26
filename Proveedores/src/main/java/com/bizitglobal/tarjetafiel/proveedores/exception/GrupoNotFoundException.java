package com.bizitglobal.tarjetafiel.proveedores.exception;

/**
 * Lanzada cuando el grupo no existe en la base de datos.
 * @author Daniel
 */
public class GrupoNotFoundException extends GrupoException {
	private static final long serialVersionUID = 8035765062869265718L;

	/**
	 * Constructor con argumentos para la excepción
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public GrupoNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
