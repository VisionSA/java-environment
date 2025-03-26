package com.bizitglobal.tarjetafiel.impuestos.exception;

/**
 * Es lanzada cuando existe en la base de datos una categoria con el mismo
 * identificador que la categoria que se quiere grabar.
 * @author Daniel
 */
public class TramosRetencionDuplicateException extends TramosRetencionException {
	private static final long serialVersionUID = 1312878675819355426L;

	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public TramosRetencionDuplicateException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
