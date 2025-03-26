package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Es lanzada cuando existe en la base de datos una ChequeEstado con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class ChequeEstadoDuplicateException extends ChequeEstadoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4070581772150674610L;

	/**
	* Constructor con argumentos para la ChequeEstado.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa de la excepcion.
	*/
	public ChequeEstadoDuplicateException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

