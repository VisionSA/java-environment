package com.bizitglobal.tarjetafiel.proveedores.negocio;

import java.math.BigInteger;

import com.bizitglobal.tarjetafiel.proveedores.exception.CbuNoValidoException;
import com.sun.org.apache.xpath.internal.operations.Mod;

public class CbuValido {
	private final String ponderador = "9713";
	
	public CbuValido(String cbu) throws CbuNoValidoException {
		if(cbu.length()==22) {
			String bloque1 = cbu.substring(0, 8);
			String bloque2 = cbu.substring(8, 22);
			
			if(!esValido(bloque1) || !esValido(bloque2)) {
				throw new CbuNoValidoException("El cbu no es valido.");
			}
		}
	}
	
	private boolean esValido(String unBloque) {
		int longitud = unBloque.length();		
		char[] tmp = unBloque.toCharArray();		
		char[] ponderador = (new String("3179")).toCharArray();
		Long digitoCalculado = new Long(0);
		Long digitoRecibido = new Long(new String(tmp,longitud-1,1));
				
		long suma = 0;
		int k, m, cte = 0;		
				
		for (int i=longitud-2; i>=0; i--){
			k = Integer.parseInt(new String(tmp,i,1));
			m = Integer.parseInt(new String(ponderador,(cte++)%4,1));
			suma += k*m;
		}
		if (suma%10 != 0){
			digitoCalculado = Long.valueOf(10 - suma%10);
		}	

		System.out.println("Numero original con DV: "+unBloque+", DV calculado: "+digitoCalculado);
		
		return digitoRecibido.equals(digitoCalculado);		
	}
	
	public static void main(String[] args) {
		try {
			new CbuValido("2650450202145056396676");
		} catch (CbuNoValidoException e) {
			e.printStackTrace();
		}
	}
}
