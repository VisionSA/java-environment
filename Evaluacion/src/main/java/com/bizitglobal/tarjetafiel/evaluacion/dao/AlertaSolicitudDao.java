package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.AlertaSolicitud;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface AlertaSolicitudDao {
	public void grabarEvaAlertasSolicitudes (AlertaSolicitud pObject);
	public AlertaSolicitud buscarEvaAlertasSolicitudes (Long id);
	public void borrarEvaAlertasSolicitudes (Long id);
	public void borrarEvaAlertasSolicitudes (AlertaSolicitud pObject);
	public void actualizarEvaAlertasSolicitudes (AlertaSolicitud pObject);
	public List listarTodos(Filtro filtro);
}

