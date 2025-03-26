package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Es lanzada cuando existe en la base de datos una MovimientoMP con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class MovimientoMPDuplicateException extends MovimientoMPException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1197145657940557704L;

	/**
	* Constructor con argumentos para la MovimientoMP.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa de la excepcion.
	*/
	public MovimientoMPDuplicateException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

