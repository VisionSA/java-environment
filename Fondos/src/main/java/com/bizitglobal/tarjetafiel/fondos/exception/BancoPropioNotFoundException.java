package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class BancoPropioNotFoundException extends BancoPropioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4441637412396178761L;

	/**
	* Constructor con argumentos para la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa raiz de la excepcion.
	*/
	public BancoPropioNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

