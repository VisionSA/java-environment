package com.bizitglobal.tarjetafiel.proveedores.exception;

/**
 * Es lanzada cuando existe en la base de datos un comprobante imputado con el mismo
 * identificador que el comprobante imputado que se quiere grabar.
 * @author Daniel
 */
public class ComprobanteImputadoDuplicateException extends ComprobanteImputadoException {
	private static final long serialVersionUID = -5610948694723631993L;

	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public ComprobanteImputadoDuplicateException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
