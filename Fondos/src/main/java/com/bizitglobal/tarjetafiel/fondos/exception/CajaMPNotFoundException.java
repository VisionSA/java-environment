package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class CajaMPNotFoundException extends CajaMPException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6474288765440175242L;

	/**
	* Constructor con argumentos para la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa raiz de la excepcion.
	*/
	public CajaMPNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

