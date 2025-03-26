package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Es lanzada cuando existe en la base de datos una EstadoInterbank con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class EstadoInterbankDuplicateException extends EstadoInterbankException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3354744077747535146L;

	/**
	* Constructor con argumentos para la EstadoInterbank.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa de la excepcion.
	*/
	public EstadoInterbankDuplicateException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

