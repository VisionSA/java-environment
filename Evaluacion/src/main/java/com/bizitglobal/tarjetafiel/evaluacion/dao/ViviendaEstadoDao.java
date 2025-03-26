package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ViviendaEstado;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface ViviendaEstadoDao {
	public void grabarEvaVivEstados (ViviendaEstado pObject);
	public ViviendaEstado buscarEvaVivEstados (Long id);
	public void borrarEvaVivEstados (Long id);
	public void borrarEvaVivEstados (ViviendaEstado pObject);
	public void actualizarEvaVivEstados (ViviendaEstado pObject);
	public List listarTodos(Filtro filtro);
}

