package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class ChequeNotFoundException extends ChequeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5008958149587258792L;

	/**
	* Constructor con argumentos para la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa raiz de la excepcion.
	*/
	public ChequeNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

