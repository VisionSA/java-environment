package com.bizitglobal.webapp.jstl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.operador.negocio.Permiso;


@SuppressWarnings({"rawtypes"})
public class JstlList {
	private static final Logger log = Logger.getLogger(JstlList.class);


	public static boolean contains(List unaLista, String unaCadena) {
		boolean result = false;
		boolean encontrado = false;

		if ((unaLista != null) && (!unaLista.isEmpty())) {
			Iterator iter = unaLista.iterator();
			while (iter.hasNext() && !encontrado) {
				Permiso permiso = (Permiso) iter.next();
				if (permiso.getNombre().equals(unaCadena)) {
					result = true;
					encontrado = true;
				}
			}
		} else {
			log.info("Lista de permisos vacia o nula!!!");
		}

		return result;
	}


	public static boolean equalsString(String uno, String dos) {
		boolean result = false;

		if (uno != null && dos != null) {
			result = uno.equals(dos);
		}

		return result;
	}

}
