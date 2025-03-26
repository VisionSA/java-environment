package com.bizitglobal.tarjetafiel.contabilidad.exception;


/**
*	Es lanzada cuando existe en la base de datos una actividad con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class EjercicioDuplicateException extends EjercicioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1493396138556929137L;

	/**
	* Constructor con argumentos para la actividad.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa de la excepción.
	*/
	public EjercicioDuplicateException (String msg, Throwable cause) {
		super(msg,cause);
	}
}

