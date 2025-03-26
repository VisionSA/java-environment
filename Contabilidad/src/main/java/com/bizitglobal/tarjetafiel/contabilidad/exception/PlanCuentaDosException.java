package com.bizitglobal.tarjetafiel.contabilidad.exception;


/**
*	Padre de las excepciones que ocurren en la clase.
*/
public class PlanCuentaDosException extends Exception{
		
	private static final long serialVersionUID = 2339830061777694154L;
		/**
		* Constructor por defecto de la excepción.
		* @param msg, Mensaje descriptivo de la excepción.
		*/
		public PlanCuentaDosException (String msg) {

			super(msg);
		}
		/**
		* Constructor con argumentos para la excepción.
		* @param msg, Mensaje descriptivo de la excepción.
		* @param cause, Causa raiz de la excepción.
		*/
		public PlanCuentaDosException (String msg, Throwable cause) {

			super(msg,cause);
		}
	}




