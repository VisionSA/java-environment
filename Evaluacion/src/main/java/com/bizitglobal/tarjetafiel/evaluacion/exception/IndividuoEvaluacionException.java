package com.bizitglobal.tarjetafiel.evaluacion.exception;
/**
*	Padre de las excepciones que ocurren en la clase.
*/
public class IndividuoEvaluacionException extends Exception {
	/**
	* Constructor por defecto de la excepción.
	* @param msg, Mensaje descriptivo de la excepción.
	*/
	public IndividuoEvaluacionException (String msg) {

		super(msg);
	}
	/**
	* Constructor con argumentos para la excepción.
	* @param msg, Mensaje descriptivo de la excepción.
	* @param cause, Causa raiz de la excepción.
	*/
	public IndividuoEvaluacionException (String msg, Throwable cause) {

		super(msg,cause);
	}
}

