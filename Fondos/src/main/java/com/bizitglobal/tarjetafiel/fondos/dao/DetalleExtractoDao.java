package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.Date;
import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.negocio.DetalleExtracto;

public interface DetalleExtractoDao {
	public void grabarDetalleExtracto (DetalleExtracto pObject);
	public DetalleExtracto buscarDetalleExtracto (Long id);
	public DetalleExtracto buscarDetalleExtractoFlex (Long id);
	public void borrarDetalleExtracto (Long id);
	public void borrarDetalleExtracto (DetalleExtracto pObject);
	public void actualizarDetalleExtracto (DetalleExtracto pObject);
	public List listarTodos(Filtro filtro);
	public List listarTodosFlex(Filtro filtro);
	public List buscarRangoConDatos(Long minFecha, Long maxFecha, Long idBancoPropio);
	public List listarTodosNoConciliadosFlex(Filtro filtro);
	public double traerSaldoExtractoHastaFecha(Long idBancoPropio, Date fechaCorte, Character conciliado);
}

