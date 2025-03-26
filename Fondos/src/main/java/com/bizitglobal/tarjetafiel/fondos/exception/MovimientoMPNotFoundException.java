package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class MovimientoMPNotFoundException extends MovimientoMPException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7139186896704232359L;

	/**
	* Constructor con argumentos para la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa raiz de la excepcion.
	*/
	public MovimientoMPNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

