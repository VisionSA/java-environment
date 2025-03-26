package com.bizitglobal.tarjetafiel.operador.exeption;

/**
 * Es lanzada cuando existe en la base de datos un rol con el mismo
 * identificador que el rol que se quiere grabar.
 * @author Daniel
 */
public class RolDuplicateException extends RolException {
	private static final long serialVersionUID = -5776840141999004609L;

	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public RolDuplicateException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
