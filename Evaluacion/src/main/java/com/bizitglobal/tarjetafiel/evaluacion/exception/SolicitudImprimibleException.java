package com.bizitglobal.tarjetafiel.evaluacion.exception;


public class SolicitudImprimibleException extends Exception{

	private static final long serialVersionUID = -8432208926611003652L;

	public SolicitudImprimibleException(String msg) {
		super(msg);
	}
	
	public SolicitudImprimibleException(String msg,Throwable cause) {
		super(msg,cause);
	}		
}