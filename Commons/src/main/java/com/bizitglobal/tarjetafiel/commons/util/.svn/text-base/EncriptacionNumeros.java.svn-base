package com.bizitglobal.tarjetafiel.commons.util;


public class EncriptacionNumeros {
	
	private String[] cero = {"A","j","Z","8"};
	private String[] uno = {"m","r","b","G"};
	private String[] dos = {"9","D","z","H"};
	private String[] tres = {"B","v","f","k"};
	private String[] cuatro = {"n","2","c","L"};
	private String[] cinco = {"y","F","3","Y"};
	private String[] seis = {"g","1","K","q"};
	private String[] siete = {"M","a","P","J"};
	private String[] ocho = {"d","N","X","w"};
	private String[] nueve = {"h","5","p","e"};
	private String[] guion = {"i","Q","W","t"};

	
	public String encriptar(String entrada){
		String salida = "";
		char[] charArray = entrada.toCharArray();
		for(int i=0;i<charArray.length;i++){
			String aux = code(charArray[i]);
			if(aux=="ERROR"){
				return aux;
			}
			salida += aux;
		}
		return salida;
	}
	
	public String desencriptar(String entrada){
		String salida = "";
		char[] charArray = entrada.toCharArray();
		for(int i=0;i<charArray.length;i++){
			String aux = decode(charArray[i]);
			if(aux=="ERROR"){
				return aux;
			}
			salida += aux;
		}
		return salida;
	}
	
	
	public String code (char in){
		int r = (int) Math.floor(Math.random()*4);
		switch(in){
			case '0': return cero[r];
			case '1': return uno[r];
			case '2': return dos[r];
			case '3': return tres[r];
			case '4': return cuatro[r];
			case '5': return cinco[r];
			case '6': return seis[r];
			case '7': return siete[r];
			case '8': return ocho[r];
			case '9': return nueve[r];
			case '-': return guion[r];
		}
		return "ERROR";
	}
	
	public String decode (char in){
		String aux = ""+in;
		if(contains(cero,aux)){return "0";}
		if(contains(uno,aux)){return "1";}
		if(contains(dos,aux)){return "2";}
		if(contains(tres,aux)){return "3";}
		if(contains(cuatro,aux)){return "4";}
		if(contains(cinco,aux)){return "5";}
		if(contains(seis,aux)){return "6";}
		if(contains(siete,aux)){return "7";}
		if(contains(ocho,aux)){return "8";}
		if(contains(nueve,aux)){return "9";}
		if(contains(guion,aux)){return "-";}
		return "ERROR";
	}
	
	
	public static <T> boolean contains(final T[] array, final T v) {
	    for (final T e : array)
	        if (e == v || v != null && v.equals(e))
	            return true;
	    return false;
	}

	
}
