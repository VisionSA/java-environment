package com.bizitglobal.tarjetafiel.proveedores.exception;

/**
 * Padre de las excepciones que ocurren en cuota comprobante.
 * @author Daniel
 */
public class CuotaComprobanteException extends Exception {
	
	/**
	 * Constructor por defecto de la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 */
	public CuotaComprobanteException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa raiz de la excepción.
	 */
	public CuotaComprobanteException(String msg,Throwable cause) {
		super(msg,cause);
	}	

}
