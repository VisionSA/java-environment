package com.bizitglobal.tarjetafiel.cobranzas.exception;


public class PlanException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 0L;
		/**
		* Constructor por defecto de la excepción.
		* @param msg, Mensaje descriptivo de la excepción.
		*/
		public PlanException(String msg) {
			super(msg);
		}
		/**
		* Constructor con argumentos para la excepción.
		* @param msg, Mensaje descriptivo de la excepción.
		* @param cause, Causa raiz de la excepción.
		*/
		public PlanException(String msg, Throwable cause) {
			super(msg,cause);
		}
}
