package com.bizitglobal.tarjetafiel.contabilidad.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class PlanCuentaDosNotFoundException extends PlanCuentaDosException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8853089210190436212L;

	/**
	* Constructor con argumentos para la excepción.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa raiz de la excepción.
	*/
	public PlanCuentaDosNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

