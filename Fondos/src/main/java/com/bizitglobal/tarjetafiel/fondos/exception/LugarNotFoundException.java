package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class LugarNotFoundException extends LugarException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4096953041035204450L;

	/**
	* Constructor con argumentos para la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa raiz de la excepcion.
	*/
	public LugarNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

