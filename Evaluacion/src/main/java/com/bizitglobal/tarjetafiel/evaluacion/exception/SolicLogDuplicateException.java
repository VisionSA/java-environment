package com.bizitglobal.tarjetafiel.evaluacion.exception;
/**
*	Es lanzada cuando existe en la base de datos una actividad con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class SolicLogDuplicateException extends SolicLogException {

	private static final long serialVersionUID = -2455692844122676736L;

	/**
	* Constructor con argumentos para la actividad.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa de la excepción.
	*/
	public SolicLogDuplicateException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

