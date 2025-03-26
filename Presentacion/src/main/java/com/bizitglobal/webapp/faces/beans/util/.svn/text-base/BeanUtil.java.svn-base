package com.bizitglobal.webapp.faces.beans.util;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;


@SuppressWarnings({"rawtypes","unchecked"})
public class BeanUtil {
	private static final Logger log = Logger.getLogger(BeanUtil.class);


	/**
	 * Muestra el contenido de una lista, los objetos contenedores deben implementar el metodo toString.
	 * 
	 * @param lista
	 *            , la lista a mostrar.
	 */
	public static void mostrarLista(List lista) {
		if (!lista.isEmpty()) {
			Iterator iter = lista.iterator();
			while (iter.hasNext()) {
				log.info(((Object) iter.next()).toString());
			}
		} else {
			log.info("Lista vacia.");
		}
	}

}
