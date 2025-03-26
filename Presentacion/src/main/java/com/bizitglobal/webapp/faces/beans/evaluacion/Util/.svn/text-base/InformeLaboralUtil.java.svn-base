package com.bizitglobal.webapp.faces.beans.evaluacion.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bizitglobal.tarjetafiel.general.negocio.Autonomo;
import com.bizitglobal.tarjetafiel.general.negocio.Ocupacion;
import com.bizitglobal.tarjetafiel.general.negocio.Rubro;
import com.bizitglobal.tarjetafiel.general.negocio.TamEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDocumento;


@SuppressWarnings({"rawtypes","unchecked"})
public class InformeLaboralUtil {

	public static List cargarAutonomos(List lstAutonomo) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Autonomo"));
		if (!lstAutonomo.isEmpty()) {
			Iterator iterator = lstAutonomo.iterator();
			while (iterator.hasNext()) {
				Autonomo element = (Autonomo) iterator.next();
				result.add(new SelectItem(element.getIdAutonomo(), element.getDescripcion()));
			}
		}
		return result;
	}


	public static List cargarTipoDocumento(List lstDocumento) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Tipo Documento"));
		if (!lstDocumento.isEmpty()) {
			Iterator iterator = lstDocumento.iterator();
			while (iterator.hasNext()) {
				TipoDocumento element = (TipoDocumento) iterator.next();
				result.add(new SelectItem(element.getIdTipoDocumento(), element.getDescripcion()));
			}
		}
		return result;
	}


	public static List cargarOcupacion(List lstOcupacion) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Ocupacion"));
		if (!lstOcupacion.isEmpty()) {
			Iterator iterator = lstOcupacion.iterator();
			while (iterator.hasNext()) {
				Ocupacion element = (Ocupacion) iterator.next();
				result.add(new SelectItem(element.getIdOcupacion(), element.getDescripcion()));
			}
		}
		return result;
	}


	public static List cargarRubros(List lstRubro) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Rubro"));
		if (!lstRubro.isEmpty()) {
			Iterator iterator = lstRubro.iterator();
			while (iterator.hasNext()) {
				Rubro element = (Rubro) iterator.next();
				result.add(new SelectItem(element.getIdRubro(), element.getDescripcion()));
			}
		}
		return result;
	}


	public static List cargarTamanioEmpresa(List lstTamanioEmpresa) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Tamaño Empresa"));
		if (!lstTamanioEmpresa.isEmpty()) {
			Iterator iterator = lstTamanioEmpresa.iterator();
			while (iterator.hasNext()) {
				TamEmpresa element = (TamEmpresa) iterator.next();
				result.add(new SelectItem(element.getIdTamanioEmp(), element.getDescripcion()));
			}
		}
		return result;
	}
}
