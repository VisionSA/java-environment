package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.general.negocio.Empresa;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface IndividuoEvaluacionDao {
	public void grabarEvaIndividuos (IndividuoEvaluacion pObject);
	public IndividuoEvaluacion buscarEvaIndividuos (Long id);
	public void borrarEvaIndividuos (Long id);
	public void borrarEvaIndividuos (IndividuoEvaluacion pObject);
	public void actualizarEvaIndividuos (IndividuoEvaluacion pObject);
	public List listarTodos(Filtro filtro);
	public List<IndividuoEvaluacion> buscarIndividuosHabilitadosCuit(Empresa empresa);
}

