package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;

import com.bizitglobal.tarjetafiel.evaluacion.exception.SolicitudIndividuoException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.SolicitudIndividuo;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface SolicitudIndividuoDao {
	public void grabarEvaSolicIndividuos (SolicitudIndividuo pObject);
	public SolicitudIndividuo buscarEvaSolicIndividuos (Long id);
	public void borrarEvaSolicIndividuos (Long id);
	public void borrarEvaSolicIndividuos (SolicitudIndividuo pObject);
	public void actualizarEvaSolicIndividuos (SolicitudIndividuo pObject);
	public List listarTodos(Filtro filtro);
	public boolean isCargarAdicionales(Solicitud solicitud);
	public boolean isCargarGarantes(Solicitud solicitud);
}

