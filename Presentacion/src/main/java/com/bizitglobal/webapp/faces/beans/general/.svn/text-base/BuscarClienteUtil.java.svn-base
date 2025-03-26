package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bizitglobal.tarjetafiel.general.negocio.Cliente;
import com.bizitglobal.webapp.faces.util.ClienteSeleccionable;


@SuppressWarnings({"rawtypes","unchecked"})
public class BuscarClienteUtil {

	public static List getClientesSeleccionables(List clientes) {
		List result = new ArrayList();
		if (!clientes.isEmpty()) {
			Iterator iter = clientes.iterator();
			while (iter.hasNext()) {
				Cliente aux = (Cliente) iter.next();
				result.add(new ClienteSeleccionable(aux, false));
			}
		}

		return result;
	}

}
