package com.bizitglobal.tarjetafiel.contabilidad.exception;


/**
*	Padre de las excepciones que ocurren en la clase.
*/
public class DocAdjuntoException extends Exception {
		

		/**
		 * 
		 */
		private static final long serialVersionUID = -4438300783744101004L;
		/*
		* Constructor por defecto de la excepción.
		* @param msg, Mensaje descriptivo de la excepción.
		*/
		public DocAdjuntoException (String msg) {

			super(msg);
		}
		/**
		* Constructor con argumentos para la excepción.
		* @param msg, Mensaje descriptivo de la excepción.
		* @param cause, Causa raiz de la excepción.
		*/
		public DocAdjuntoException (String msg, Throwable cause) {

			super(msg,cause);
		}
	}




