package com.bizitglobal.tarjetafiel.contabilidad.exception;


/**
*	Padre de las excepciones que ocurren en la clase.
*/
public class EjercicioException extends Exception {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = -6356758609501928585L;
		/**
		* Constructor por defecto de la excepción.
		* @param msg, Mensaje descriptivo de la excepción.
		*/
		public EjercicioException (String msg) {

			super(msg);
		}
		/**
		* Constructor con argumentos para la excepción.
		* @param msg, Mensaje descriptivo de la excepción.
		* @param cause, Causa raiz de la excepción.
		*/
		public EjercicioException (String msg, Throwable cause) {

			super(msg,cause);
		}
	}




