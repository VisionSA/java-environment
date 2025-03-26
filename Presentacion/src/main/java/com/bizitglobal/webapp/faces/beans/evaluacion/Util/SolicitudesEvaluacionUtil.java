package com.bizitglobal.webapp.faces.beans.evaluacion.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.webapp.faces.beans.evaluacion.wrappers.SolicitudesSeleccionables;


@SuppressWarnings({"rawtypes","unchecked"})
public class SolicitudesEvaluacionUtil {
	private static final Logger log = Logger.getLogger(SolicitudesEvaluacionUtil.class);


	public static List solicitudesSeleccionables(List solicitudes) {
		List result = new ArrayList();

		if (!solicitudes.isEmpty()) {
			Iterator iter = solicitudes.iterator();
			while (iter.hasNext()) {
				Solicitud solicitud = (Solicitud) iter.next();
				solicitud.getEstados().getDescripcion();
				SolicitudesSeleccionables temp = new SolicitudesSeleccionables();
				temp.setSolicitud(solicitud);
				result.add(temp);
			}
		}
		return result;
	}

}