package com.bizitglobal.tarjetafiel.evaluacion.negocio;

public class NroVerificador {
	
	public static String generarDV(String numero){
		Long digito = new Long(0);
		int longitud = numero.length();		
		char[] tmp = numero.toCharArray();		
		long suma = 0;
		int k, producto;
		int cte = 1;
		
		for (int i=longitud-1; i>=0; i--){
			k = Integer.parseInt(new String(tmp,i,1));			
			producto = ((cte++)%2 + 1)*k;
			suma += (producto/10) + (producto%10);
		}
		if (suma%10 != 0){
			digito = Long.valueOf(10 - suma%10);
		}		
		
		return digito.toString();
	}
	
	public static boolean esValido(String numero, String DV){
		return DV.equals(generarDV(numero));		
	}
	
}