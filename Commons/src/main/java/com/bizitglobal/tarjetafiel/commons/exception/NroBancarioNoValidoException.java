package com.bizitglobal.tarjetafiel.commons.exception;

/**
 * Es lanzada cuando se intenta crear un cuit que no es valido.
 * @author Daniel
 */
public class NroBancarioNoValidoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6300684527135002661L;

	/**
	 * Constructor por defecto de la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 */
	public NroBancarioNoValidoException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa raiz de la excepción.
	 */
	public NroBancarioNoValidoException(String msg,Throwable cause) {
		super(msg,cause);
	}	

}
