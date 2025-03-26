package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Es lanzada cuando existe en la base de datos una CajaApertura con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class CajaAperturaDuplicateException extends CajaAperturaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6436418570735517577L;

	/**
	* Constructor con argumentos para la CajaApertura.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa de la excepcion.
	*/
	public CajaAperturaDuplicateException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

