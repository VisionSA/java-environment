package com.bizitglobal.tarjetafiel.contabilidad.exception;


/**
*	Es lanzada cuando existe en la base de datos una actividad con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class TipoAsientoDuplicateException extends TipoAsientoException {


	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5877367525947134915L;

	/**
	* Constructor con argumentos para la actividad.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa de la excepción.
	*/
	public TipoAsientoDuplicateException (String msg, Throwable cause) {
		super(msg,cause);
	}
}

