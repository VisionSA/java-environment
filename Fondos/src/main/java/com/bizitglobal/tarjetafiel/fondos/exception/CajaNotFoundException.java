package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class CajaNotFoundException extends CajaException {



	/**
	 * 
	 */
	private static final long serialVersionUID = -6813837247357275367L;

	/**
	* Constructor con argumentos para la excepción.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa raiz de la excepción.
	*/
	public CajaNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

