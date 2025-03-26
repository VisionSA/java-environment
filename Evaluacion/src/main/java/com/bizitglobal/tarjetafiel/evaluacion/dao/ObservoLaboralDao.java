package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.ObservoLaboral;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface ObservoLaboralDao {
	public void grabarEvaObsLaborales (ObservoLaboral pObject);
	public ObservoLaboral buscarEvaObsLaborales (Long id);
	public void borrarEvaObsLaborales (Long id);
	public void borrarEvaObsLaborales (ObservoLaboral pObject);
	public void actualizarEvaObsLaborales (ObservoLaboral pObject);
	public List listarTodos(Filtro filtro);
}

