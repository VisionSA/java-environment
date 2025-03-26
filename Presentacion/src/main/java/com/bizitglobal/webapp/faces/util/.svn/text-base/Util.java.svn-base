package com.bizitglobal.webapp.faces.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;
import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.commons.util.Mascara;




@SuppressWarnings({"rawtypes","unchecked"})
public class Util {

	/**
	 * Completa una cadena con 0 segun la longitud seteada.
	 * 
	 * @param cadena
	 *            a completar.
	 * @param digitos
	 *            , longitud de digitos a completar.
	 * @return cadena armada.
	 */
	public static String completar(String cadena, int digitos) {
		String result = null;

		if (cadena != null && digitos != 0 && cadena.length() > 0 && digitos > 0) {
			int resto = (cadena.length() - digitos) * (-1);
			String aux = "";
			for (int i = 0; i < resto; i++) {
				aux += "0";
			}
			result = aux + cadena;
		}

		return result;
	}


	/**
	 * Crea una lista de SelectItem a partir de una lista de objetos de negocio, estos deben implementar la interfas Negocio de Commons
	 * 
	 * @param objList
	 * @return una lista de SelectItem
	 */
	public static List cargarSelectItem(List objList) {
		List result = new ArrayList();
		if (objList != null && !objList.isEmpty()) {
			Iterator iterObj = objList.iterator();
			while (iterObj.hasNext()) {
				Negocio aux = (Negocio) iterObj.next();
				result.add(new SelectItem(aux.getId(), aux.getLabel()));
			}
		}
		return result;
	}


	/**
	 * Limpia el contenido de una lista, en caso de ser nula crea una nueva instancia
	 * 
	 * @param objList
	 * @return una lista vacia
	 */
	public static List limpiarLista(List objList) {
		if (objList != null)
			objList.clear();
		else
			objList = new ArrayList();
		return objList;
	}


	/**
	 * Crea una lista de SelectItem a partir de una lista de objetos de Mascara,
	 * 
	 * @param objList
	 * @return una lista de SelectItem
	 */
	public static List cargarSelectItemMascara(Mascara[] objList) {
		List result = new ArrayList();
		for (int i = 0; i < objList.length; i++) {
			Mascara masc = objList[i];
			result.add(new SelectItem(masc.getValorEnBaseDatos(), masc.getMascara()));
		}
		return result;
	}


	/**
	 * @deprecated Crea un SelectItem con el value en 0 de tipo Long
	 * @param label
	 * @return
	 */
	public static SelectItem primerSelectItem(String label) {
		SelectItem item = new SelectItem(new Long(0), label);
		return item;
	}


	/**
	 * Busca un objeto dentro de una lista de objetos de negocio que tienen sobrescrito el metodo equals comparado por el id.
	 * 
	 * @param list
	 *            , lista de objetos de negocio.
	 * @param buscado
	 *            , objeto a buscar.
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
	 * id:4991 Busca un objeto Negocio dentro de una lista de objetos de negocio por su Id
	 * 
	 * @return el objeto de negocio encontrado o null si no se encuentra.
	 */
	public static Object buscarElemento(List list, Long id) {
		Object obj2 = null;
		for (Object obj : list) {
			Negocio objNegocio = (Negocio) obj;
			if (objNegocio.getId().equals(id)) {
				obj2 = obj;
			}
		}
		return obj2;
	}


	/**
	 * Quita el relleno de ceros usado como prefijo
	 * 
	 * @param object
	 *            , objeto q tiene el relleno de ceros
	 * @return el objeto como string sin rellenos
	 */
	public static String quitarRelleno(Object valor) {
		String cadena = valor.toString();
		int cont = 0;
		for (int i = 0; i < cadena.length(); i++) {
			if (cadena.charAt(i) != '0') {
				break;
			}
			cont++;
		}
		return cadena.substring(cont, cadena.length());
	}


	/* @I4629 *//**
	 * Dado el numero del mes devuelve el nombre correspondiente
	 * 
	 * @param nroMes
	 *            1,2,...,12
	 * @return nombre del mes
	 */
	public static String nombreMes(int nroMes) {
		if (nroMes >= 1 && nroMes <= 12) {
			String[] Meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre",
					"Diciembre" };
			return Meses[nroMes - 1];
		}
		else
			return null;

	}
}
/* @F4629 */