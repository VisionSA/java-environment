package com.bizitglobal.webapp.faces.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;


@SuppressWarnings({"rawtypes","unchecked"})
public class Convertidores {
	private static final Logger log = Logger.getLogger(Convertidores.class);


	/**
	 * Convierte un set en un list.
	 * 
	 * @param unSet
	 *            , Set a convertir.
	 * @return Un list equivalente.
	 */
	public static List setToList(Set unSet) {
		// log.info(" convirtiendo un set en list...");
		List result = new ArrayList(unSet);
		// if(!unSet.isEmpty()) {
		// Iterator sets = unSet.iterator(); REEMPLAZADO ESTE MEOLLO CON UN SIMPLE CONSTRUCTOR
		// while(sets.hasNext()) {
		// log.info("	convertiendo");
		// result.add(sets.next());
		// }
		// }

		return result;
	}


	// public static Set listToSet(List list) {
	// // log.info(" convirtiendo un list en set...");
	// Set result = new HashSet();
	// if(!list.isEmpty()) {
	// Iterator sets = list.iterator();
	// while(sets.hasNext()) {
	// ProveedorFormaPago fdp = (ProveedorFormaPago)sets.next();
	// log.info("Cuenta:"+fdp.getFormaPago().getDescripcion());
	// log.info("C.Fondos:"+fdp.getNroCuentaFondos());
	// result.add(fdp);
	// }
	// }
	//
	// return result;
	// }
	/**
	 * Devuelve un String de longitudDeseada caracteres y que finaliza en valorOriginal. La cadena se completa por la izquierda con los caracteres de
	 * la cadena conQueCompletar (que debe tener 3 caracteres, si no dispara Exception) Si la longitud pedida es negativa omenor a la del entero
	 * pasado dispara una Exception. conQueCompletar debe ser una cadena de 3 caracteres. Ejemplo completarALaIzquierda(32, 8, "000") retorna
	 * "00000032"
	 * 
	 * @param valorOriginal
	 *            es en entero que deseamos rellenar con otra cadena a la izquierda.
	 * @param longitudDeseada
	 *            representa la longitud final de la cadena a devolver, incluye los lugares completados y el parametro valorOriginal.
	 * @param conQueCompletar
	 *            debe ser una cadena de tres caracteres (si no lo es dispara una Exception) con los caracteres con que se completara la cadena
	 * @exception Exception
	 *                S
	 * @return Una cadena de longitudDeseada que comienza con la Cadena conQueCompletar y finaliza con valorOriginal
	 * */
	public static String completarALaIzquierda(int valorOriginal, int longitudDeseada, String conQueCompletar) throws Exception {
		String aux = "";
		if (conQueCompletar.length() != 3)
			throw new Exception();
		int longitudDelCaracter = String.valueOf(valorOriginal).length();
		if (longitudDeseada < longitudDelCaracter)
			throw new Exception();
		int lugaresACompletar = longitudDeseada - longitudDelCaracter;
		int restoPorTres = lugaresACompletar % 3;
		int cociente = lugaresACompletar / 3;
		for (int i = 0; i < cociente; i++) {
			aux += conQueCompletar;
		}
		String complemento = conQueCompletar.substring(0, restoPorTres);
		aux += complemento + valorOriginal;
		return aux;
	}


	// idem anterior, pero para el caso de necesitar un valor mas grande...
	public static String completarALaIzquierda(long valorOriginal, int longitudDeseada, String conQueCompletar) throws Exception {
		String aux = "";
		if (conQueCompletar.length() != 3)
			throw new Exception();
		int longitudDelCaracter = String.valueOf(valorOriginal).length();
		if (longitudDeseada < longitudDelCaracter)
			throw new Exception();
		int lugaresACompletar = longitudDeseada - longitudDelCaracter;
		int restoPorTres = lugaresACompletar % 3;
		int cociente = lugaresACompletar / 3;
		for (int i = 0; i < cociente; i++) {
			aux += conQueCompletar;
		}
		String complemento = conQueCompletar.substring(0, restoPorTres);
		aux += complemento + valorOriginal;
		return aux;
	}

}
