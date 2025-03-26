package com.bizitglobal.tarjetafiel.impuestos.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Retencion;

public interface RetencionDao {
	
	public void grabarRetencion(Retencion unaRetencion);
	
	public Retencion buscarRetencion(Long id);
	
	public void borrarRetencion(Long id);
	
	public void borrarRetencion(Retencion unaRetencion);
	
	public List listarTodos(Filtro filtro);
	
	public void actualizarRetencion(Retencion unaRetencion);
	
}
