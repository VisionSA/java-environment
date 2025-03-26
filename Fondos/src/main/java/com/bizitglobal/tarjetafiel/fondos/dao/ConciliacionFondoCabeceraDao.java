package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondoCabecera;

public interface ConciliacionFondoCabeceraDao {
	public void grabarConciliacionFondoCabecera (ConciliacionFondoCabecera pObject);
	public ConciliacionFondoCabecera buscarConciliacionFondoCabecera (Long id);
	public void borrarConciliacionFondoCabecera (Long id);
	public void borrarConciliacionFondoCabecera (ConciliacionFondoCabecera pObject);
	public void actualizarConciliacionFondoCabecera (ConciliacionFondoCabecera pObject);
	public List listarTodos(Filtro filtro);
/*@I3918*/	public List<ConciliacionFondoCabecera> buscarConciliacionCabeceraPorFecha(int tipoFecha, Date fechaDesde, Date fechaHasta, Long idBancoPropio, String conciliado, int firstResult, int maxResults, String numero, Double importe);
	public List<ConciliacionFondoCabecera> listarPaginado(final Filtro filtro, final int firstResult, final int maxResults);
	public Long cantidadRegistros(Filtro filtro);
/*@I3918*/	public Long cantidadRegistrosPorFecha(int tipoFecha, Date fechaDesde, Date fechaHasta, Long idBancoPropio, String conciliado, String numero, Double importe);
}

