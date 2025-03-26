package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.InformeLaboral;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface InformeLaboralDao {
	public void grabarEvaInfoLaborales (InformeLaboral pObject);
	public InformeLaboral buscarEvaInfoLaborales (Long id);
	public void borrarEvaInfoLaborales (Long id);
	public void borrarEvaInfoLaborales (InformeLaboral pObject);
	public void actualizarEvaInfoLaborales (InformeLaboral pObject);
	public List listarTodos(Filtro filtro);
}

