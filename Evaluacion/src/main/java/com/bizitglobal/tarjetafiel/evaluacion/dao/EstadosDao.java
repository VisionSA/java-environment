package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Estados;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface EstadosDao {
	public void grabarEvaEstados (Estados pObject);
	public Estados buscarEvaEstados (Long id);
	public void borrarEvaEstados (Long id);
	public void borrarEvaEstados (Estados pObject);
	public void actualizarEvaEstados (Estados pObject);
	public List listarTodos(Filtro filtro);
}

