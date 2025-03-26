package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Es lanzada cuando existe en la base de datos una DetalleExtracto con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class DetalleExtractoDuplicateException extends DetalleExtractoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5626656727811513038L;

	/**
	* Constructor con argumentos para la DetalleExtracto.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa de la excepcion.
	*/
	public DetalleExtractoDuplicateException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

