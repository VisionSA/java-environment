package com.bizitglobal.tarjetafiel.commons.util;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class Convertidores {
	private static final Logger log = Logger.getLogger(Convertidores.class);

	/**
	 * Convierte un set en un list.
	 * @param unSet, Set a convertir.
	 * @return Un list equivalente.
	 */
	public static List setToList(Set unSet) {
//		List result = new ArrayList();
//		if(unSet != null && !unSet.isEmpty()) {
//			Iterator sets = unSet.iterator();
//			while(sets.hasNext()) {
//				result.add(sets.next());
//			}
//		}
//		return result;
		return new ArrayList(unSet);
	}
	
	
	public static Set listToSet(List list) {
//		Set result = new HashSet();
//		if(!list.isEmpty()) {
//			Iterator sets = list.iterator();
//			while(sets.hasNext()) {
//				result.add(sets.next());
//			}
//		}
//		return result;		
		return new HashSet(list);
	}
	
	public static List copiarListaString(List lista) {
		List result = new ArrayList();
		if(!lista.isEmpty()) {
			Iterator iter = lista.iterator();
			while(iter.hasNext()) {
				String cadena = new String(iter.next().toString());
				result.add(cadena);
			}
		}
		return result;
	}
	
	public static Calendar timestampToCalendar(Timestamp timestamp){
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(new Date(timestamp.getTime()));
		return fecha;
	}
	
	public static String getDateFormat(Date unaFecha) {
		Format dateFormat = new SimpleDateFormat("''yyyy-MM-dd''");
		return dateFormat.format(unaFecha);
	}
	
	public static String getDateTimeFormat(Date unaFecha) {
		Format datetimeFormat = new SimpleDateFormat("''yyyy-MM-dd HH:mm:ss''");	
		return datetimeFormat.format(unaFecha);
	}
	
	public static String getSorN(boolean b){
//		if (b) {
//			return "S";
//		} else {
//			return "N";
//		}
		return b? "S":"N";
	}
	
	public static boolean getBoolean(String val){
		return val.trim().equals("S");
	}
	
	public static Date getdate(String fecha) {
		return new Date(fecha);
	}
	
	public static String getdate(Date fecha) {
		return fecha.toString();
	}
	
	/**
	 * Devuelve un String de longitudDeseada caracteres y que finaliza en valorOriginal. La cadena se completa
	 * por la izquierda con los caracteres de la cadena conQueCompletar (que debe tener 3 caracteres, si no dispara Exception)
	 * Si la longitud pedida es negativa o menor a la del entero pasado dispara una Exception.
	 * conQueCompletar debe ser una cadena de 3 caracteres.
	 * Ejemplo completarALaIzquierda(32, 8, "000") retorna "00000032"
	 * 
	 * @param valorOriginal es en entero que deseamos rellenar con otra cadena a la izquierda.
	 * @param longitudDeseada representa la longitud final de la cadena a devolver, incluye los lugares completados y el parametro valorOriginal.
	 * @param conQueCompletar debe ser una cadena de tres caracteres (si no lo es dispara una Exception) con los caracteres con que se completara la cadena
	 * @exception Exception S
	 * @return Una cadena de longitudDeseada que comienza con la Cadena conQueCompletar y finaliza con valorOriginal
	 * */
	public String completarALaIzquierda(int valorOriginal, int longitudDeseada, String conQueCompletar) throws Exception {
		String aux = "";
		if (conQueCompletar.length()!=3) throw new Exception();
		int longitudDelCaracter = String.valueOf(valorOriginal).length();
		if (longitudDeseada < longitudDelCaracter) throw new Exception();
		int lugaresACompletar = longitudDeseada - longitudDelCaracter;
		int restoPorTres = lugaresACompletar%3;
		int cociente = lugaresACompletar/3;
		for (int i=0; i<cociente; i++) {
			aux += conQueCompletar;
		}
		String complemento = conQueCompletar.substring(0,restoPorTres);
		aux += complemento + valorOriginal;
	    return aux;
	}
	
	/**
	 * Devuelve un String de longitudDeseada caracteres y que finaliza en valorOriginal. La cadena se completa
	 * por la izquierda con los caracteres de la cadena conQueCompletar 
	 * Si la longitud pedida es negativa o menor a la del entero pasado dispara una Exception.
	 * conQueCompletar debe ser una cadena de 3 caracteres.
	 * Ejemplo completarALaIzquierda("32", '0', 8) retorna "00000032"
	 * 
	 * @param valorOriginal un String que deseamos rellenar con otra cadena a la izquierda.
	 * @param conQueCompletar debe ser un Character con el caracteres con que se completara la cadena
	 * @param longitudDeseada representa la longitud final de la cadena a devolver, incluye los lugares completados y el parametro valorOriginal.
	 * @exception Exception S
	 * @return Una cadena de longitudDeseada que comienza con la Cadena conQueCompletar y finaliza con valorOriginal
	 * */
	public static String completarIzquierda(String valorOriginal, Character conQueCompletar, int longitudDeseada) throws Exception {
//		String aux = "";
//		if (conQueCompletar.length()!=3) throw new Exception();
//		int longitudDelCaracter = String.valueOf(valorOriginal).length();
//		if (longitudDeseada < longitudDelCaracter) throw new Exception();
//		int lugaresACompletar = longitudDeseada - longitudDelCaracter;
//		int restoPorTres = lugaresACompletar%3;
//		int cociente = lugaresACompletar/3;
//		for (int i=0; i<cociente; i++) {
//			aux += conQueCompletar;
//		}
//		String complemento = conQueCompletar.substring(0,restoPorTres);
//		aux += complemento + valorOriginal;
//	    return aux;
	    
	    String result = "";
		if(valorOriginal!=null && longitudDeseada>=0) {
			int resto = (valorOriginal.length() - longitudDeseada) * -1;
			for(int i=0;i<resto;i++) {
				result += conQueCompletar;
			}
			result = result+valorOriginal;
		}else{
			throw new Exception();
		}
		
		return result;
	    
	}
	
	/**
	 * Devuelve un String de longitudDeseada caracteres y que finaliza en valorOriginal. La cadena se completa
	 * por la derecha con los caracteres de la cadena conQueCompletar 
	 * Si la longitud pedida es negativa o menor a la del entero pasado dispara una Exception.
	 * conQueCompletar debe ser una cadena de 3 caracteres.
	 * Ejemplo completarDerecha("32", '0', 8) retorna "32000000"
	 * 
	 * @param valorOriginal un String que deseamos rellenar con otra cadena a la derecha.
	 * @param conQueCompletar debe ser un Character con el caracteres con que se completara la cadena
	 * @param longitudDeseada representa la longitud final de la cadena a devolver, incluye los lugares completados y el parametro valorOriginal.
	 * @exception Exception S
	 * @return Una cadena de longitudDeseada que comienza con la Cadena conQueCompletar y finaliza con valorOriginal
	 * */
	public static String completarDerecha(String valorOriginal, Character conQueCompletar, int longitudDeseada) throws Exception {    
	    String result = "";
		if(valorOriginal!=null && longitudDeseada>=0) {
			int resto = (valorOriginal.length() - longitudDeseada) * -1;
			for(int i=0;i<resto;i++) {
				result += conQueCompletar;
			}
			result = valorOriginal + result;
		}else{
			throw new Exception();
		}		
		return result;
	    
	}
	
	public static double round(double unValor) {
		double result = unValor;
		result = result*100;
		result = java.lang.Math.round(result);
		result = result/100;
		return result;
	}
}
