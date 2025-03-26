package com.bizitglobal.tarjetafiel.contabilidad.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class LoteDetalleNotFoundException extends LoteDetalleException {

	



	/**
	 * 
	 */
	private static final long serialVersionUID = -6267285127366335533L;

	/**
	* Constructor con argumentos para la excepción.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa raiz de la excepción.
	*/
	public LoteDetalleNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

