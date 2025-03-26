package com.bizitglobal.tarjetafiel.commons.util;

import java.util.ArrayList;
import java.util.List;
public class Util {
	
	/**
	 * Completa una cadena con 0 segun la longitud seteada.
	 * @param cadena a completar.
	 * @param digitos, longitud de digitos a completar.
	 * @return cadena armada.
	 */
	public static String completar(String cadena, int digitos) {
		String result = null;
		
		if(cadena!=null && digitos!=0 && cadena.length()>0 && digitos>0) {
			int resto = (cadena.length() - digitos) * (-1);
			String aux = "";
			for(int i=0;i<resto;i++) {
				aux += "0";
			}
			result = aux+cadena;
		}
		
		return result;
	}
	
	public static String formatString(String cadena,int longitud){
		String retorno =""; 
		for(int i= 0 ;i < longitud ;i++){
			retorno +=  " ";
		}
		retorno += cadena;
		
		retorno= retorno.substring(retorno.length()-longitud, retorno.length());
		
		if(retorno.equals("null")){
			for(int i= 0 ;i < longitud ;i++){
				retorno +=  " ";
			}
		}
		return retorno;
	}
	
	/**
	 * Limpia el contenido de una lista, en caso de ser nula crea una nueva instancia
	 * @param objList
	 * @return una lista vacia
	 */
	public static List limpiarLista(List objList) {
		if(objList!=  null)
			objList.clear();
		else objList =  new ArrayList();
		return objList;
	}
	
	
	/**
	 * Busca un objeto dentro de una lista de objetos de negocio que 
	 * tienen sobrescrito el metodo equals comparado por el id. 
	 * @param list, lista de objetos de negocio.
	 * @param buscado, objeto a buscar.
	 * @return el objeto encontrado o null si no habia un objeto con el mismo id.
	 */
	public static Object buscarElemento(List list, Object buscado) {
		Object obj = null;
		if (buscado != null) {
			int i = list.indexOf(buscado);
			if (i >= 0) {
				obj = list.get(i);
				return obj;
			}
		}
		return obj;
	}
	
	
	/**
	 *Quita el relleno de ceros usado como prefijo
	 * @param object, objeto q tiene el relleno de ceros
	 * @return el objeto como string sin rellenos
	 */
	public static String quitarRelleno(Object valor){
		String cadena= valor.toString();
		 int cont=0;
		 for(int i= 0;i<cadena.length();i++){
		   if(cadena.charAt(i) != '0'){
			   break;
		   }
		   cont++;
		 }
		 return cadena.substring(cont,cadena.length());
		}
		
	  /**
     *  @param anio
	 * @return el ultimo dia del mes de febrero
     * 
     */

		public static int ultimoDiaFebrero(int anio){
		  if(anio%4==0)
			  return 29 ;
		      return 28 ;
		}
		
		
		/**
		 * @id: 4655
		 * Method: autoCompletarString
		 * Description: Autocompleta un string con el caracter dado 
		 * @param cadena
		 * @param tamanio
		 * @return
		 */
		public static String autoCompletarString(String cadena, Character caracter, int tamanio){
			if(cadena.length()>tamanio){
				return cadena.substring(0, tamanio);
			}else{
				while(cadena.length()<tamanio){
					cadena+=caracter;
				}
				return cadena;
			}		
		}
		
		/**
		 * @id: 4655
		 * Method: generarDigito
		 * Description: de Wilkipedia: 
		 * 				Dígito de control. Para comprobar el dígito de control (por ejemplo, por el ordenador y el 
		 * 				escáner de código de barras), se suman los dígitos de las posiciones pares, el resultado se 
		 * 				multiplica por 3, se le suman los dígitos de las posiciones impares y este resultado se le 
		 * 				resta a su múltiplo de 10 más próximo. 
		 * 				El resultado final ha de coincidir con el dígito de control.
		 * @param cod
		 */
		public static String generarDigitoVerificadorCB(String cod) {
			int sumaPares = 0;
			int sumaImpares = 0;
			int sumaTotal = 0;
			
			for (int i=0; i<cod.length(); i++){
				if (i%2 == 0){
					sumaPares += Integer.parseInt(String.valueOf(cod.charAt(i))) * 3;
				}
				else
				{
					sumaImpares += Integer.parseInt(String.valueOf(cod.charAt(i)));
				}
			}
			
			sumaTotal = sumaImpares + sumaPares;
			
			int mul10Prox = sumaTotal / 10;
			mul10Prox = mul10Prox * 10;
			if (mul10Prox < sumaTotal)
			{
				mul10Prox += 10;
			}
			return ((Integer)(mul10Prox - sumaTotal)).toString();
		}
	
	}

 
	

