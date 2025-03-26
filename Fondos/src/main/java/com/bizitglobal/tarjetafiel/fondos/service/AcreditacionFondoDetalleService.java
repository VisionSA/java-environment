package com.bizitglobal.tarjetafiel.fondos.service;

import java.util.List;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.interfaces.Paginacion;
import com.bizitglobal.tarjetafiel.fondos.exception.AcreditacionFondoDetalleException;
import com.bizitglobal.tarjetafiel.fondos.exception.AcreditacionFondoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondo;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondoDetalle;


public interface AcreditacionFondoDetalleService extends Paginacion{
	
	
	public void grabarAcreditacionFondoDetalle(AcreditacionFondoDetalle acreditacionFondoDetalle)throws AcreditacionFondoDetalleException ;
	
	
	public AcreditacionFondoDetalle leerAcreditacionFondoDetalle(Long id) throws AcreditacionFondoDetalleException;
	
	
	public void borrarAcreditacionFondoDetalle(Long id) throws AcreditacionFondoDetalleException;
	
	
	public void borrarAcreditacionFondoDetalle(AcreditacionFondoDetalle acreditacionFondoDetalle) throws AcreditacionFondoDetalleException;
	
	
	public void actualizarAcreditacionFondoDetalle(AcreditacionFondoDetalle acreditacionFondoDetalle) throws AcreditacionFondoDetalleException;
	
	
	public List getAcreditacionesDetalle() throws AcreditacionFondoDetalleException;
	
	
	public List getAcreditacionesDetalle(Filtro filtro) throws AcreditacionFondoDetalleException;
	
	/**
	 * Retorna un List<Long> que representan fechas (Long<20111217>) almacenadas en la base t_vis_fon_acredit_detalle para un banco determinado.  
	 * @param minFecha: la fecha minima que trae el archivo
	 * @param maxFecha: la fecha maxima que trae el archivo
	 * @param idBanco:  id de banco del archivo
	 * @return
	 * @throws AcreditacionFondoDetalleException
	 */
	public List buscarRangoConDatos(final Long minFecha, final Long maxFecha, final Long idBanco)throws AcreditacionFondoDetalleException;
}
