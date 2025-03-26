package com.bizitglobal.tarjetafiel.commons.util;


import com.bizitglobal.tarjetafiel.commons.exception.TarjetaNoValidaException;


public class TarjetaValida {
	private static int MAX_CANT_DIGITOS= 16;  
	private Long nroTarjeta;
	private int[] mascara=new int[MAX_CANT_DIGITOS-1];// le sacamos el digito verificador
	private char[] digitos;
	int sumaVerificadora=0;
	private int digitoVerificador; 
	
	
	
	public TarjetaValida(String unNroTarjeta) throws TarjetaNoValidaException {
		
		//for(int i=0;i<unNroTarjeta.length();i++)
		//	 System.out.println(Character.getNumericValue(unNroTarjeta.charAt(i)));
		     
		
		String subcadena =null;
		inicializarmascara();
		digitoVerificador= Character.getNumericValue(unNroTarjeta.charAt(MAX_CANT_DIGITOS-1));
		System.out.println(digitoVerificador);
		System.out.println(unNroTarjeta);
		if(unNroTarjeta.length()==MAX_CANT_DIGITOS){
			unNroTarjeta= unNroTarjeta.substring(0,MAX_CANT_DIGITOS-1 );  // aca le sacamos el digito verificador 
			digitos=unNroTarjeta.toCharArray();
			for(int i= 0;i<digitos.length;i++){
			    if(i%2 == 0)		
				 mascara[i]= Character.getNumericValue(unNroTarjeta.charAt(i))*2;
			    else   mascara[i]= Character.getNumericValue(unNroTarjeta.charAt(i));
		   }  
			
			for(int i=0;i<mascara.length;i++){
				 if(mascara[i]>9){
					subcadena= String.valueOf(mascara[i]);
					sumaVerificadora+=Character.getNumericValue(subcadena.charAt(0))+ Character.getNumericValue(subcadena.charAt(1));
				 }
				 else sumaVerificadora+= mascara[i]; 
			}
			System.out.println("la suma es "+sumaVerificadora);
			
			if(digitoVerificador!=0){
				int key=0;
				if(sumaVerificadora <100)
				      key= Character.getNumericValue(String.valueOf(sumaVerificadora).charAt(0));
				 else key= Character.getNumericValue(String.valueOf(sumaVerificadora).charAt(1));
				String prefijoRedondeo=null;  
				switch (key) {
				    case 1:
				    	prefijoRedondeo="2"; 
						break;
		            case 2:
		            	prefijoRedondeo="3"; 
		    	     	break;
		            case 3:
		            	prefijoRedondeo="4"; 
		    			break;
		            case 4:
		            	prefijoRedondeo="5"; 
		    			break;
		            case 5:
		            	prefijoRedondeo="6"; 
		    	     	break; 
		            case 6:
		            	prefijoRedondeo="7"; 
		    			break;
		            case 7:
		            	prefijoRedondeo="8"; 
		    			break;
		            case 8:
		            	prefijoRedondeo="9";
		            case 9:
		            	prefijoRedondeo="0"; 
		        	
		       		default:
						break;
				}
				String redondeo= null;
				if(sumaVerificadora <100){
				    if(sumaVerificadora!= 99) 
					   redondeo= prefijoRedondeo+"0";
				    else redondeo="100";
				}else{
					redondeo= String.valueOf(sumaVerificadora).charAt(0)+prefijoRedondeo+"0"; 
				}
		        if(Integer.parseInt(redondeo)-sumaVerificadora !=digitoVerificador)
		        	throw new TarjetaNoValidaException("El  nro de tarjeta no es válido.");
				  
				
				
			}else  {    if(Character.getNumericValue(String.valueOf(sumaVerificadora).charAt(0))!=0)
	                    	throw new TarjetaNoValidaException("El  nro de tarjeta no es válido.");
				  }
			
			}
	 		
			
			
		
		else throw new TarjetaNoValidaException("El  nro de tarjeta no es válido.");
		 
	}

	
	
	private void inicializarmascara(){
		for(int i=0;i<mascara.length;i++){
			mascara[i]= 0;
		}
		
		
	}
	
	public static void main(String[] args) {
		try {
			TarjetaValida cuit1  = new TarjetaValida("5399045678910510");
		    
			
			
		} catch (TarjetaNoValidaException e) {
			e.printStackTrace();
		}
	}



	public int getDigitoVerificador() {
		return digitoVerificador;
	}



	public void setDigitoVerificador(int digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}
}
