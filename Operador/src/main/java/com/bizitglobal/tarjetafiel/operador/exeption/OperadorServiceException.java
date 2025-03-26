package com.bizitglobal.tarjetafiel.operador.exeption;

/**
 * Se lanza cuando un operador no posee permisos para algun servicio.
 * @author Daniel
 */
public class OperadorServiceException extends Throwable {

	/**
	 * Constructor por defecto para la excepción.
	 */	
	public OperadorServiceException() {
		super();
	}

	/**
	 * Constructor con argumentos.
	 * @param msg, Mensaje descriptivo para la excepción.
	 */	
	public OperadorServiceException(String e) {
		super(e);
	}

}
