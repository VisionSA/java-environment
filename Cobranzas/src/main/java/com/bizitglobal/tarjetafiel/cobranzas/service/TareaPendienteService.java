package com.bizitglobal.tarjetafiel.cobranzas.service;

import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.cobranzas.exception.EjecucionPlanException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.TareaPendiente;

public interface TareaPendienteService {
	
	public List listaTareasPendientes() throws Exception;
	
	
	public List listarDetallesTarea(TareaPendiente tareaP,String estado) throws Exception;
	
	public void ejecutarConfirmacionAsignacionCobradores(TareaPendiente tareaP) throws Exception;
	
	public List getListaTareasByParam(String estado,Date fechaDesde, Date fechaHasta) throws EjecucionPlanException;
	
	public void generarPlanillasCobradores(Date fecha);	
	
	public List getListaDetallesTareasByParam(Date fecha,Long idAccion, String estado);
	
	public void confirmarTareaPendiente(Date fecha, int tipoTarea,Long idAccion);
	
	public List getListaTareasByIdCliente(Long idCliente) throws Exception;
	
}
