package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class AsientoItemNotFoundException extends AsientoItemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4443504956107210604L;

	/**
	* Constructor con argumentos para la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa raiz de la excepcion.
	*/
	public AsientoItemNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

