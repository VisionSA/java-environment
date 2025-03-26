package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Padre de las excepciones que ocurren en la clase.
*/
public class AcreditacionFondoDetalleException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2713681286197429164L;
	/**
	* Constructor por defecto de la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	*/
	public AcreditacionFondoDetalleException (String msg) {

		super(msg);
	}
	/**
	* Constructor con argumentos para la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa raiz de la excepcion.
	*/
	public AcreditacionFondoDetalleException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

