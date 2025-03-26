package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Es lanzada cuando existe en la base de datos una ExtractoBancario con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class ExtractoBancarioDuplicateException extends ExtractoBancarioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4533437226173119651L;

	/**
	* Constructor con argumentos para la ExtractoBancario.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa de la excepcion.
	*/
	public ExtractoBancarioDuplicateException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

