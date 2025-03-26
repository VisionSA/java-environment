package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Es lanzada cuando existe en la base de datos una AsientoItem con el mismo
*	identificador que la actividad que se quiere grabar.
*/
public class AsientoItemDuplicateException extends AsientoItemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1083019419988125098L;

	/**
	* Constructor con argumentos para la AsientoItem.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa de la excepcion.
	*/
	public AsientoItemDuplicateException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

