package com.bizitglobal.tarjetafiel.commons.util;


import org.apache.log4j.Logger;

/**
 * Esta clase permite especificar un valor para la base de datos, y la mascara con la cual uno 
 * pretende que el usuario la reconozca. Por ejemplo si, en la base de datos 
 * 1: (representa Clientes)
 * 2: (representa Proveedores)
 * entonces podemos crear una mascara para cada valor, que sera new Mascara(new Integer(1),"Clientes");
 * Nota: ver que el primer parametro puede ser Long, Integer o String;
 * */
public class Mascara {
	private static final Logger log = Logger.getLogger(Mascara.class);
    private Object valorEnBaseDatos;
    private String mascara;
    
    public Mascara(Object valorEnBaseDatos,String mascara) {
    	if (valorEnBaseDatos instanceof Long || valorEnBaseDatos instanceof Integer || valorEnBaseDatos instanceof String) {
    		this.valorEnBaseDatos = valorEnBaseDatos;
	    	this.mascara = mascara;
    	}
    }
    
	public String getMascara() {
		return mascara;
	}

	public Object getValorEnBaseDatos() {
		return valorEnBaseDatos;
	}
	
	public class MascaraException extends Exception {
		
		/**
		* Constructor por defecto de la excepcion.
		* @param msg, Mensaje descriptivo de la excepcion.
		*/
		public MascaraException (String msg) {
			super(msg);
		}
		
	}
	
}