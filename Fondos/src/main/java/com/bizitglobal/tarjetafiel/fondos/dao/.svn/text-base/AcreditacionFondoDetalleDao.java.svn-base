package com.bizitglobal.tarjetafiel.fondos.dao;

import java.util.List;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.paginacion.Paginador;
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;
import com.bizitglobal.tarjetafiel.fondos.negocio.AcreditacionFondoDetalle;

public interface AcreditacionFondoDetalleDao {

	public void grabarAcreditacionFondoDetalle (AcreditacionFondoDetalle pObject);
	public AcreditacionFondoDetalle buscarAcreditacionFondoDetalle (Long id);
	public void borrarAcreditacionFondoDetalle (Long id);
	public void borrarAcreditacionFondoDetalle (AcreditacionFondoDetalle pObject);
	public void actualizarAcreditacionFondoDetalle (AcreditacionFondoDetalle pObject);
	public List listarTodos(Filtro filtro);
	public Page listarTodosPage(Filtro filtro, final int pageNumber, final int pageSize);
	public List listarTodosPagina(Filtro filtro, final Paginador paginador);
	public List buscarRangoConDatos(Long minFecha, Long maxFecha, Long idBanco);
}
