package com.bizitglobal.tarjetafiel.proveedores.exception;

/**
 * Lanzada cuando el DocumentoAdjunto no existe en la base de datos.
 * @author Waldemar
 */
public class DocumentoAdjuntoNotFoundException extends DocumentoAdjuntoException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1099276618079927579L;

	/**
	 * Constructor con argumentos para la excepción
	 * @param msg, Mensaje descriptivo de la excepción.
	 * @param cause, Causa de la excepción.
	 */
	public DocumentoAdjuntoNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
