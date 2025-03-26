package com.bizitglobal.webapp.faces.beans.operador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.operador.exeption.RolException;
import com.bizitglobal.tarjetafiel.operador.negocio.Rol;
import com.bizitglobal.tarjetafiel.operador.service.RolService;


@SuppressWarnings({"rawtypes","unchecked"})
public class OperadorUtil {
	private Logger log = Logger.getLogger(OperadorUtil.class);


	public static List cargarRoles(RolService service) {
		List result = new ArrayList();
		try {
			List rolesList = service.getRoles();
			if (!rolesList.isEmpty()) {
				Iterator roles = rolesList.iterator();
				while (roles.hasNext()) {
					Rol rol = (Rol) roles.next();
					SelectItem item = new SelectItem();
					item.setValue(rol.getIdRol());
					item.setLabel(rol.getDescripcion());
					result.add(item);
				}
			}
		} catch (RolException e) {
			e.printStackTrace();
		}

		return result;
	}

}