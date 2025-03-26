package com.bizitglobal.tarjetafiel.impuestos.exception;

/**
 * Es lanzada cuando existe en la base de datos un impuesto con el mismo
 * identificador que el impuesto que se quiere grabar.
 * @author Daniel
 */
public class ImpuestoDuplicateException extends ImpuestoException {
	private static final long serialVersionUID = -5610948694723631993L;

	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public ImpuestoDuplicateException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
