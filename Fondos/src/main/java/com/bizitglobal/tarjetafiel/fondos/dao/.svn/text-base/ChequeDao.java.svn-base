package com.bizitglobal.tarjetafiel.fondos.dao;
import java.util.List;
import java.util.Map;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.paginacion2.Page;
import com.bizitglobal.tarjetafiel.fondos.negocio.Cheque;

public interface ChequeDao {
	public void grabarCheque (Cheque pObject);
	public Cheque buscarCheque (Long id);
	public void borrarCheque (Long id);
	public void borrarCheque (Cheque pObject);
	public void actualizarCheque (Cheque pObject);
	public List listarTodos(Filtro filtro);
	public Page listarTodosPage(Filtro filtro, final int pageNumber, final int pageSize);
	public Map obtenerUpload(String listINidCheque );
	public void actualizarTodosProcesados(String listINidCheque);
	public Long contarChequePendiente (final Filtro filtro ) ;
	public List getChequesByParam(Filtro filtro);
	public Long actualizarAcreditacionesConciliadas();
}

