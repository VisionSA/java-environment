package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Es lanzada cuando existe en la base de datos una Chequera con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class ChequeraDuplicateException extends ChequeraException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7168089014260131203L;

	/**
	* Constructor con argumentos para la Chequera.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa de la excepcion.
	*/
	public ChequeraDuplicateException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

