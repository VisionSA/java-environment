package com.bizitglobal.tarjetafiel.evaluacion.dao;
import java.util.List;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Educacion;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;

public interface EducacionDao {
	public void grabarEvaEducaciones (Educacion pObject);
	public Educacion buscarEvaEducaciones (Long id);
	public void borrarEvaEducaciones (Long id);
	public void borrarEvaEducaciones (Educacion pObject);
	public void actualizarEvaEducaciones (Educacion pObject);
	public List listarTodos(Filtro filtro);
}

