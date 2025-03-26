package com.bizitglobal.tarjetafiel.proveedores.exception;

/**
 * Es lanzada cuando existe en la base de datos una cuota comprobante con el mismo
 * identificador que la cuota comprobante que se quiere grabar.
 * @author Daniel
 */
public class CuotaComprobanteDuplicateException extends CuotaComprobanteException {
	private static final long serialVersionUID = -5610948694723631993L;

	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public CuotaComprobanteDuplicateException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
