package com.bizitglobal.tarjetafiel.commons.util;


import com.bizitglobal.tarjetafiel.commons.exception.NroBancarioNoValidoException;


public class NroBancarioValido {
	private String nroBancario;
	//private int[] tupla = {9,7,1,3};
	private int[] tupla = {3,1,7,9};
	private String tipo; // indica el tipo de nro bancario (ej : cheque,cbu,nroCuenta, etc)
	private static int CANT_ELEM_TUPLA= 4; 
	public static String CHEQUE = "Cheque";
	
	public NroBancarioValido(String unNroBancario,String tipo) throws NroBancarioNoValidoException {
		
		  int longitudNroBancarioSinVerificador= unNroBancario.length()-1;
		  int verificador=0;
		   try{
			    verificador = new Integer(unNroBancario.substring(longitudNroBancarioSinVerificador,longitudNroBancarioSinVerificador+1)).intValue();   
		   }catch(NumberFormatException e){
		
			   throw new NroBancarioNoValidoException("El nro de "+tipo+" no es válido.");
		   }
	
		   String nroBancarioSinVerificador= unNroBancario.substring(0,unNroBancario.length()-1);
		   
		   int cantTuplas= longitudNroBancarioSinVerificador/CANT_ELEM_TUPLA;
		   int restoTupla= longitudNroBancarioSinVerificador%CANT_ELEM_TUPLA;
		   int sumatoria=0;
		   int index = longitudNroBancarioSinVerificador-1;
		 	for(int i=0;i<cantTuplas;i++) {
				for(int j=0;j<CANT_ELEM_TUPLA;j++){
					sumatoria+= Integer.parseInt(String.valueOf(nroBancarioSinVerificador.charAt(index)))*tupla[j];
					if(index >0)
					    index--;
				}
			}
			for(int i=0;i<restoTupla;i++){
			   sumatoria+= 	Integer.parseInt(String.valueOf(nroBancarioSinVerificador.charAt(index)))*tupla[i];
			   if(index >0)
				    index--;
			   
			}
			
			String unidadSumatoria=null;
			String cadenaSumatoria =   new Integer(sumatoria).toString();
			unidadSumatoria= cadenaSumatoria.substring(cadenaSumatoria.length()-1,cadenaSumatoria.length());
		  /*if(sumatoria>=10){
				 if(sumatoria<100)
		    	    unidadSumatoria= new Integer(sumatoria).toString().substring(1,2);
				 else   unidadSumatoria= new Integer(sumatoria).toString().substring(2,3);
			}else{ 
				     unidadSumatoria=    new Integer(sumatoria).toString();
			      }*/
			
			if(unidadSumatoria.compareTo("0")==0){
			      if(verificador!=0)  throw new NroBancarioNoValidoException("El nro de "+tipo+" no es válido.");        	
			} else {  if(verificador!= 10- Integer.parseInt(unidadSumatoria))
			           throw new NroBancarioNoValidoException("El nro de "+tipo+" no es válido.");
		 	}	
			
			
				
			nroBancario =unNroBancario;
			this.tipo=tipo;
		
	}

	public String getNumeroBancario() {
		return nroBancario;
	}
	
	public String getTipo() {
		return tipo;
	}

	
	public String getVerificador() {
		return nroBancario.substring(nroBancario.length()-1,nroBancario.length());   
	}
	
	public static boolean nroBancarioValido(String unNroBancario,String tipo) {
		try {
			if (unNroBancario != null) {
				new NroBancarioValido(unNroBancario,tipo);
				return true;
			}
		} catch (NroBancarioNoValidoException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		try {
			NroBancarioValido nro1  = new NroBancarioValido("01704710","CBU 1er bloque");
			System.out.println("nro 1 ->"+"nroBancario:"+nro1.getNumeroBancario()+"Ver:"+nro1.getVerificador()+" Tipo: " +nro1.getTipo());
			
			NroBancarioValido nro2  = new NroBancarioValido("4161231","Cheque");
			System.out.println("nro 2 ->"+"nroBancario:"+nro2.getNumeroBancario()+"Ver:"+nro2.getVerificador()+" Tipo: " +nro2.getTipo());
			
			NroBancarioValido nro3  = new NroBancarioValido("200000024376","Cuenta");
			System.out.println("nro 3 ->"+"nroBancario:"+nro3.getNumeroBancario()+"Ver:"+nro3.getVerificador()+" Tipo: " +nro3.getTipo());
	
			NroBancarioValido nro4  = new NroBancarioValido("01747154003","Cuenta");
			System.out.println("nro 4 ->"+"nroBancario:"+nro4.getNumeroBancario()+"Ver:"+nro4.getVerificador()+" Tipo: " +nro4.getTipo());
	
			NroBancarioValido nro5  = new NroBancarioValido("20000000024374","CBU 2do bloque");
			System.out.println("nro 5 ->"+"nroBancario:"+nro5.getNumeroBancario()+"Ver:"+nro5.getVerificador()+" Tipo: " +nro5.getTipo());
			
	
			/*este son otros CBU  para probar: 07201765-30000024861947
			 * 01105407 - 30054000100629
			 * 02900384-00000000038496
			 * 0860060801800000026517
			 * 01105520-30055208993679
			 * 0070117020000003965944
			 * 0200922701000000869147
			 * 0200915901000000372029
			 * 0200452901000002551415
			 * 02009180 11000002718680
			 * 01677771-00001707119125
			 * 0070010820000013117133
			 * 01103845-40038411197530
			 * 02009180  11000002718680   
			 * 01677771-00001707119125   
			 * 0110127620012711170529 
			 */
			
			
			

			NroBancarioValido nro8  = new NroBancarioValido("01105520","CBU 1do bloque");
			System.out.println("nro 8 ->"+"nroBancario:"+nro8.getNumeroBancario()+"Ver:"+nro8.getVerificador()+" Tipo: " +nro8.getTipo());
			
			NroBancarioValido nro9  = new NroBancarioValido("30055208993679","CBU 2do bloque");
			System.out.println("nro 9 ->"+"nroBancario:"+nro9.getNumeroBancario()+"Ver:"+nro9.getVerificador()+" Tipo: " +nro9.getTipo());

			
			
	        
			
			
		
			
		} catch (NroBancarioNoValidoException e) {
			e.printStackTrace();
		}
	}
}
