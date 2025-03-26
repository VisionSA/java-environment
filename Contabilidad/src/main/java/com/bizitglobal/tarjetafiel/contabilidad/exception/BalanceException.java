package com.bizitglobal.tarjetafiel.contabilidad.exception;


/**
*	Padre de las excepciones que ocurren en la clase.
*/
public class BalanceException extends Exception {
		


		/**
	 * 
	 */
	private static final long serialVersionUID = -7151057479666734577L;
		/**
		* Constructor por defecto de la excepción.
		* @param msg, Mensaje descriptivo de la excepción.
		*/
		public BalanceException (String msg) {

			super(msg);
		}
		/**
		* Constructor con argumentos para la excepción.
		* @param msg, Mensaje descriptivo de la excepción.
		* @param cause, Causa raiz de la excepción.
		*/
		public BalanceException (String msg, Throwable cause) {

			super(msg,cause);
		}
	}




