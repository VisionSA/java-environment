package com.bizitglobal.tarjetafiel.contabilidad.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class AsientoNotFoundException extends AsientoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2575574531783225862L;

	/**
	 * 
	 */


	/**
	* Constructor con argumentos para la excepción.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa raiz de la excepción.
	*/
	public AsientoNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

