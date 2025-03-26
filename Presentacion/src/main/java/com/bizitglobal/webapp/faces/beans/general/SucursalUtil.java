package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorDomicilio;




@SuppressWarnings({"rawtypes","unchecked"})
public class SucursalUtil {
	private static Logger log = Logger.getLogger(SucursalUtil.class);


	public static List eliminarDomicilio(List listaDomicilios, Long idDomicilio) {
		List result = new ArrayList();
		if (!listaDomicilios.isEmpty()) {
			Iterator domicilios = listaDomicilios.iterator();
			while (domicilios.hasNext()) {
				ProveedorDomicilio aux = (ProveedorDomicilio) domicilios.next();
				if (!idDomicilio.equals(aux.getDomicilio().getIdDomicilio())) {
					result.add(aux);
				}
			}
		}

		return result;
	}


	public static Domicilio buscarDomicilio(List listaDomicilios, Long idDomicilio) {
		Domicilio result = new Domicilio();
		if (!listaDomicilios.isEmpty()) {
			Iterator domicilios = listaDomicilios.iterator();
			while (domicilios.hasNext()) {
				ProveedorDomicilio aux = (ProveedorDomicilio) domicilios.next();
				log.info("id p/ comparar: " + aux.getDomicilio().getIdDomicilio());
				if (idDomicilio.equals(aux.getDomicilio().getIdDomicilio())) {
					result = aux.getDomicilio();
				}
			}
		}

		return result;
	}

}
