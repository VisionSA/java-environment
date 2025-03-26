package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Es lanzada cuando existe en la base de datos una ChequeConciliado con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class ChequeConciliadoDuplicateException extends ChequeConciliadoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3147531615967637627L;

	/**
	* Constructor con argumentos para la ChequeConciliado.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa de la excepcion.
	*/
	public ChequeConciliadoDuplicateException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

