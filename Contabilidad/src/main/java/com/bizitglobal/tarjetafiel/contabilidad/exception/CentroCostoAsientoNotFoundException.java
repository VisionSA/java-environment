package com.bizitglobal.tarjetafiel.contabilidad.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class CentroCostoAsientoNotFoundException extends CentroCostoAsientoException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5821244620101278966L;

	/**
	 * 
	 */


	/**
	* Constructor con argumentos para la excepción.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa raiz de la excepción.
	*/
	public CentroCostoAsientoNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

