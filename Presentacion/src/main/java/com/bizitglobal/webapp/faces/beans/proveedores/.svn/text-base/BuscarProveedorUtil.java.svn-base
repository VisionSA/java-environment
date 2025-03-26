package com.bizitglobal.webapp.faces.beans.proveedores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.webapp.faces.util.ProveedorSeleccionable;


@SuppressWarnings({"rawtypes","unchecked"})
public class BuscarProveedorUtil {

	public static List getProveedoresSeleccionables(List proveedores) {
		List result = new ArrayList();
		if (!proveedores.isEmpty()) {
			Iterator iter = proveedores.iterator();
			while (iter.hasNext()) {
				Proveedor aux = (Proveedor) iter.next();
				result.add(new ProveedorSeleccionable(aux, false));
			}
		}

		return result;
	}

}
