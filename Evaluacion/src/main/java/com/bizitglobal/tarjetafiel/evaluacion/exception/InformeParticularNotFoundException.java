package com.bizitglobal.tarjetafiel.evaluacion.exception;
/**
*	Lanzada cuando el objeto no existe en la base de datos.
*/
public class InformeParticularNotFoundException extends InformeParticularException {
	private static final long serialVersionUID = -8268157569413364731L;

	/**
	* Constructor con argumentos para la excepción.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa raiz de la excepción.
	*/
	public InformeParticularNotFoundException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

