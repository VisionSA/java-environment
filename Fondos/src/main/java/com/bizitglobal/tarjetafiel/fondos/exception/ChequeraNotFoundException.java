package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class ChequeraNotFoundException extends ChequeraException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4513888542853284079L;

	/**
	* Constructor con argumentos para la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa raiz de la excepcion.
	*/
	public ChequeraNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

