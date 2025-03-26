package com.bizitglobal.tarjetafiel.proveedores.exception;


public class SubDiarioIvaException extends Exception {


	private static final long serialVersionUID = -689854037842962656L;


	public SubDiarioIvaException(String msg){
		super(msg);
	}
	
	
	public SubDiarioIvaException(String msg, Throwable cause){
		super(msg, cause);
	}
}