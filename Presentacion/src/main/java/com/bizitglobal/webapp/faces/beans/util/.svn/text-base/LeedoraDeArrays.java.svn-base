package com.bizitglobal.webapp.faces.beans.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Autor Krumrick, Waldemar Si necesitamos pasar un array de script a un bean podemos armar un string y esta clase nos recuperara los objetos.
 * */
@SuppressWarnings({"rawtypes","unchecked"})
public class LeedoraDeArrays {

	private String cadena;


	public LeedoraDeArrays(String cadena) {
		this.cadena = cadena;
	}


	public void quitarCadena(String cadenaAQuitar) {
		String aux[] = cadena.split(",");
		String auxQuitar[] = cadenaAQuitar.split(",");
		List cade = new ArrayList();
		List cadeASacar = new ArrayList();
		for (int i = 0; i < aux.length; i++) {
			cade.add(aux[i]);
		}
		for (int j = 0; j < auxQuitar.length; j++) {
			cadeASacar.add(auxQuitar[j]);
		}
		cade.removeAll(cadeASacar);
		String result = "";
		boolean primero = true;
		Iterator iter = cade.iterator();
		while (iter.hasNext()) {
			if (primero) {
				result += iter.next();
				primero = false;
			} else {
				result += ", " + iter.next();
			}
		}
		this.cadena = result;
	}


	public String next() {
		String cad = "";
		for (int i = 0; i < cadena.length(); i++) {
			if (String.valueOf(cadena.charAt(i)).compareTo(",") == 0) {
				cadena = cadena.substring(i + 1);
				break;
			} else {
				cad += cadena.charAt(i);
			}
			if ((i + 1) == cadena.length()) {
				cadena = "";
			}
		}
		return cad.trim();
	}


	public boolean isHaySiguiente() {
		if (cadena.length() > 0)
			return true;
		return false;
	}

}
