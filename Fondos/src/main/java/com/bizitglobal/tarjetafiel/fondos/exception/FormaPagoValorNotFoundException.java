package com.bizitglobal.tarjetafiel.fondos.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class FormaPagoValorNotFoundException extends FormaPagoValorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1905672299860562936L;

	/**
	* Constructor con argumentos para la excepcion.
	* @param msg, Mensaje descriptivo de la excepcion.
	* @param cause, Causa raiz de la excepcion.
	*/
	public FormaPagoValorNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

