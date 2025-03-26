package com.bizitglobal.tarjetafiel.impuestos.exception;

/**
 * Es lanzada cuando existe en la base de datos una categoria con el mismo
 * identificador que la categoria que se quiere grabar.
 * @author Daniel
 */
public class ImpuestosIndividuoDuplicateException extends ImpuestosIndividuoException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4533028999679631487L;

	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public ImpuestosIndividuoDuplicateException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
