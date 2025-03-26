package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class ChequeHistorialNotFoundException extends ChequeHistorialException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 886892435077491512L;

	/**
	* Constructor con argumentos para la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa raiz de la excepcion.
	*/
	public ChequeHistorialNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

