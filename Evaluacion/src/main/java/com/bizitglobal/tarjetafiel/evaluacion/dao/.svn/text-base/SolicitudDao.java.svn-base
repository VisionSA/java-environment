package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Solicitud;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface SolicitudDao {
	public void grabarSolicitudes (Solicitud pObject);
	public Solicitud buscarSolicitudes (Long id);
	public void borrarSolicitudes (Long id);
	public void borrarSolicitudes (Solicitud pObject);
	public void actualizarSolicitudes (Solicitud pObject);
	public List listarTodos(Filtro filtro);
	public Solicitud busNroSolicitud(Filtro filtro);
	public Long maxNroSolicitud();
}

