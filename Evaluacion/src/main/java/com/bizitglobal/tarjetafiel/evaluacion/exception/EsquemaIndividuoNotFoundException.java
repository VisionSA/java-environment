package com.bizitglobal.tarjetafiel.evaluacion.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class EsquemaIndividuoNotFoundException extends EsquemaIndividuoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7460699155310558499L;

	/**
	* Constructor con argumentos para la excepción.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa raiz de la excepción.
	*/
	public EsquemaIndividuoNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

