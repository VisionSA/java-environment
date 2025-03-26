package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Observo;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface ObservoDao {
	public void grabarEvaObservos (Observo pObject);
	public Observo buscarEvaObservos (Long id);
	public void borrarEvaObservos (Long id);
	public void borrarEvaObservos (Observo pObject);
	public void actualizarEvaObservos (Observo pObject);
	public List listarTodos(Filtro filtro);
}

