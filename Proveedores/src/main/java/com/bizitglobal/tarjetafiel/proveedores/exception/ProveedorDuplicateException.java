package com.bizitglobal.tarjetafiel.proveedores.exception;

/**
 * Es lanzada cuando existe en la base de datos un proveedor con el mismo
 * identificador que el permiso que se quiere grabar.
 * @author Daniel
 */
public class ProveedorDuplicateException extends ProveedorException {
	private static final long serialVersionUID = -5610948694723631993L;

	/**
	 * Constructor con argumentos para la excepción.
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public ProveedorDuplicateException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
