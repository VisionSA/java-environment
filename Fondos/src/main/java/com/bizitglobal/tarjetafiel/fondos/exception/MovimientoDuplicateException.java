package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Es lanzada cuando existe en la base de datos una Movimiento con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class MovimientoDuplicateException extends MovimientoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9171999199664363139L;

	/**
	* Constructor con argumentos para la Movimiento.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa de la excepcion.
	*/
	public MovimientoDuplicateException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

