package com.bizitglobal.tarjetafiel.cobranzas.dao;


import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.cobranzas.exception.EjecucionPlanException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.EjecucionPlanAsignacionCobrador;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.TareaPendiente;

public interface TareaPendienteDao {
	
	public List listarTareasPendientes();
	

	public List<EjecucionPlanAsignacionCobrador> listarDetallesTareaAsignacionCobrador(TareaPendiente tareaP, String estado);
	
	
	public List getListaTareasByParam(String estado,Date fechaDesde, Date fechaHasta) throws EjecucionPlanException;


	public List getListaDetallesTareasByParam(Date fecha, Long i, String estado);


	public List<EjecucionPlanAsignacionCobrador> listarDetallesTareaEnvioCarta(TareaPendiente tareaP,
			String estado);	
	
	public List<EjecucionPlanAsignacionCobrador> listarDetallesTareaEnvioCartaParaConfirmar(Date fecha, Long i, String estado);
	
	public List<EjecucionPlanAsignacionCobrador> listarDetallesTareaLlamadasTelefonicas(Date fecha, Long idAccion, String estado);


	List<EjecucionPlanAsignacionCobrador> listarDetallesTareaTeledirecto(
			Date fecha, Long idAccion, String estado);


	public List<EjecucionPlanAsignacionCobrador> listarDetallesTareaAsigncionAbogado(
			Date fecha, Long long1, String estado);


	List<EjecucionPlanAsignacionCobrador> listarDetallesTareaAsigncionAbogadoParaConfirmar(
			Date fecha, Long idAccion, String estado);


	public List getListaTareasByIdCliente(Long idCliente);
	
}
