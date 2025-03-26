package com.bizitglobal.tarjetafiel.proveedores.exception;

public class ProveedorCtaCteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6951987125810152204L;

	
	public ProveedorCtaCteException(String msg){
		super(msg);
	}
	
	
	public ProveedorCtaCteException(String msg, Throwable cause){
		super(msg, cause);
	}
}